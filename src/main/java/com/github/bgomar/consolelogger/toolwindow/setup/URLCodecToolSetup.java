package com.github.bgomar.consolelogger.toolwindow.setup;

import com.ibm.icu.impl.CaseMapImpl;
import com.intellij.ui.components.JBTextField;
import com.github.bgomar.consolelogger.tools.URLTools;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class URLCodecToolSetup extends AbstractToolSetup {

    private final JBTextField decodedTextArea;
    private final JBTextField encodedTextArea;
    private final JBTextField svg2CssTextArea;
    private final JTextPane preview;

    public URLCodecToolSetup(JBTextField decodedTextArea,
                             JBTextField encodedTextArea,
                             JBTextField svg2CssTextArea,
                             JTextPane preview) {
        this.decodedTextArea = decodedTextArea;
        this.encodedTextArea = encodedTextArea;
        this.svg2CssTextArea = svg2CssTextArea;
        this.preview = preview;
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
                // Create a data URI for the SVG image
                String data = svg2CssTextArea.getText();
                String html = "<html>\n<head>\n<title>d</title>\n</head>\n<body>  <div style=\"'" + data + "'); width: 100%; height: 100%;" +
                        "\"></div></body></html>";
                // Define a Safelist of allowed HTML tags and attributes
                Safelist safelist = Safelist.relaxed()
                        .addTags("svg", "rect", "circle", "path") // Add SVG tags
                        .addAttributes(":all", "style") // Allow all tags to have a "style" attribute
                        .addAttributes("circle", "cx", "cy", "r", "fill") // Allow circle attributes
                        .addAttributes("rect", "x", "y", "width", "height", "fill"); // Allow rect attributes

                // Validate the HTML code using JSoup
                String cleanHtml = Jsoup.clean(html, safelist);

                // Set the clean HTML code as the text of the preview component
                preview.setText(cleanHtml);

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
                // Create a data URI for the SVG image
                String data = svg2CssTextArea.getText();
                String html = "<html><body><div style=\"'" + data + "'); width: 100%; height: 100%;\"></div></body></html>";
                // Define a Safelist of allowed HTML tags and attributes
                Safelist safelist = Safelist.relaxed()
                        .addTags("svg", "rect", "circle", "path") // Add SVG tags
                        .addAttributes(":all", "style") // Allow all tags to have a "style" attribute
                        .addAttributes("circle", "cx", "cy", "r", "fill") // Allow circle attributes
                        .addAttributes("rect", "x", "y", "width", "height", "fill"); // Allow rect attributes

                // Validate the HTML code using JSoup
                String cleanHtml = Jsoup.clean(html, safelist);

                // Set the clean HTML code as the text of the preview component
                preview.setText(cleanHtml);
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
                // Create a data URI for the SVG image
                String data = svg2CssTextArea.getText();
                String html = "<html><body><div style=\"'" + data + "'); width: 100%; height: 100%;\"></div></body></html>";
                // Define a Safelist of allowed HTML tags and attributes
                Safelist safelist = Safelist.relaxed()
                        .addTags("svg", "rect", "circle", "path") // Add SVG tags
                        .addAttributes(":all", "style") // Allow all tags to have a "style" attribute
                        .addAttributes("circle", "cx", "cy", "r", "fill") // Allow circle attributes
                        .addAttributes("rect", "x", "y", "width", "height", "fill"); // Allow rect attributes

                // Validate the HTML code using JSoup
                String cleanHtml = Jsoup.clean(html, safelist);

                // Set the clean HTML code as the text of the preview component
                preview.setText(cleanHtml);

            }
        });
    }
}