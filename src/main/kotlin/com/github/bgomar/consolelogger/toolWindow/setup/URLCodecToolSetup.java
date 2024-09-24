package com.github.bgomar.consolelogger.toolWindow.setup;


import com.github.bgomar.consolelogger.tools.URLTools;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class URLCodecToolSetup extends AbstractToolSetup {

    private JBTextField decodedTextArea;
    private JBTextField encodedTextArea;
    private JBTextField svg2CssTextArea;
    private final JTextPane preview;

    public URLCodecToolSetup(JBTextField decodedTextArea,
                             JBTextField encodedTextArea,
                             JBTextField svg2CssTextArea,
                             JTextPane  preview)  {
        this.decodedTextArea = decodedTextArea;
        this.encodedTextArea = encodedTextArea;
        this.svg2CssTextArea = svg2CssTextArea;
        this.preview = preview;

        preview.setEditable(true);
        String html = getString();
        preview.setPreferredSize(new Dimension(250, 145));
        preview.setMinimumSize(new Dimension(10, 10));
        new URLCodecToolSetup(html);
    }

    private static @NotNull String getString() {
        String data = "background-image: url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg'%3E %3Ccircle r='50' cx='50' cy='50' " +
                "fill='tomato'/%3E %3Ccircle r='41' cx='47' cy='50' fill='orange'/%3E %3Ccircle r='33' cx='48' cy='53' fill='gold'/%3E %3Ccircle r='25' cx='49' cy='51' fill='yellowgreen'/%3E %3Ccircle r='17' cx='52' cy='50' fill='lightseagreen'/%3E %3Ccircle r='9' cx='55' cy='48' fill='teal'/%3E %3C/svg%3E\");";
        return "<html>\n<head>\n<title>d</title>\n</head>\n<body>  <div style=\"'" + data + "'); width: 100%; height: 100%;" +
                "\"></div></body></html>";
    }


    public URLCodecToolSetup(String s) {
        this.preview = new JTextPane();
        this.preview.setText(s);

    }

    public void setup() {
        decodedTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                encodedTextArea.setText(URLTools.encodeURL(decodedTextArea.getText()));
                svg2CssTextArea.setText(URLTools.svg2cssURL(encodedTextArea.getText()));

            }
        });
        encodedTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                decodedTextArea.setText(URLTools.decodeURL(encodedTextArea.getText()));
                svg2CssTextArea.setText(URLTools.svg2cssURL(encodedTextArea.getText()));


            }
        });
        svg2CssTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                encodedTextArea.setText(URLTools.css2svgURL(svg2CssTextArea.getText()));
                decodedTextArea.setText(URLTools.decodeURL(encodedTextArea.getText()));



            }
        });
    }
}
