package com.github.bgomar.consolelogger.toolwindow.setup;

import com.github.bgomar.consolelogger.tools.px2RemTool;
import com.intellij.ui.components.JBTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Px2RemToolSetup extends AbstractToolSetup {

    private final JBTextField px2RemTextField;
    private final JBTextField rem2PxTextField;

    public Px2RemToolSetup(JBTextField px2RemTextField,
                           JBTextField rem2PxTextField) {
        this.px2RemTextField = px2RemTextField;
        this.rem2PxTextField = rem2PxTextField;
    }

    public void setup() {
        px2RemTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                rem2PxTextField.setText(String.valueOf(px2RemTool.px2Rem(Double.valueOf(px2RemTextField.getText()))));
            }
        });
        rem2PxTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                px2RemTextField.setText(String.valueOf(px2RemTool.rem2Px(Double.valueOf(rem2PxTextField.getText()))));
            }
        });
    }
}