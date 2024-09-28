package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.tools.Svg2css;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import com.intellij.ui.components.JBTextField;




public class Svg2cssToolSetup extends AbstractToolSetup {

    private JBTextField svg2cssEncodedTextArea;
    private JBTextField svg2cssDecodedTextArea;
    private JBTextField svg2CssTextArea;
    private final JTextPane preview;

    public Svg2cssToolSetup(JBTextField svg2cssDecodedTextArea,
                             JBTextField svg2cssEncodedTextArea,
                             JBTextField svg2CssTextArea,
                             JTextPane  preview) throws IOException {
        this.svg2cssDecodedTextArea = svg2cssDecodedTextArea;
        this.svg2cssEncodedTextArea = svg2cssEncodedTextArea;
        this.svg2CssTextArea = svg2CssTextArea;
        this.preview = preview;

        preview.setEditable(true);
        String html = getString();
        preview.setPreferredSize(new Dimension(250, 145));
        preview.setMinimumSize(new Dimension(10, 10));
        new Svg2cssToolSetup(html);
    }

    private static @NotNull String getString() {
        String data = "background-image: url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg'%3E %3Ccircle r='50' cx='50' cy='50' " +
                "fill='tomato'/%3E  %3C/svg%3E\");";
        return "<html><head></head><body>  <div style=\"' + data + '); width: 100%; height: 100%;\"></div></body></html>";
    }

    public Svg2cssToolSetup(String s) {
        this.preview = new JTextPane();
        this.preview.setText(s);

    }

    public void setup() {
        svg2cssDecodedTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                svg2cssEncodedTextArea.setText(Svg2css.encodeURL(svg2cssDecodedTextArea.getText()));
                svg2CssTextArea.setText(Svg2css.svg2cssURL(svg2cssEncodedTextArea.getText()));

            }
        });
        svg2cssEncodedTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                svg2cssDecodedTextArea.setText(Svg2css.decodeURL(svg2cssEncodedTextArea.getText()));
                svg2CssTextArea.setText(Svg2css.svg2cssURL(svg2cssEncodedTextArea.getText()));


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

                svg2cssEncodedTextArea.setText(Svg2css.css2svgURL(svg2CssTextArea.getText()));
                svg2cssDecodedTextArea.setText(Svg2css.decodeURL(svg2cssEncodedTextArea.getText()));



            }
        });
    }
}
