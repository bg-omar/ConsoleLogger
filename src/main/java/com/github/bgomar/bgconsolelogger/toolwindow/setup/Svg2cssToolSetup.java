package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.tools.Svg2css;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Svg2cssToolSetup extends AbstractToolSetup {

    private JBTextField svg2cssEncodedTextArea;
    private JBTextField svg2cssDecodedTextArea;
    private JBTextField svg2CssTextArea;

    public Svg2cssToolSetup(JBTextField svg2cssDecodedTextArea,
                             JBTextField svg2cssEncodedTextArea,
                             JBTextField svg2CssTextArea)  {
        this.svg2cssDecodedTextArea = svg2cssDecodedTextArea;
        this.svg2cssEncodedTextArea = svg2cssEncodedTextArea;
        this.svg2CssTextArea = svg2CssTextArea;
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
