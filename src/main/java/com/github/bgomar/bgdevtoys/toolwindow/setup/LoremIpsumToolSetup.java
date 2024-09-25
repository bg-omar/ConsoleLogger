package com.github.bgomar.bgdevtoys.toolwindow.setup;

import com.github.bgomar.bgdevtoys.tools.LoremIpsumTools;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoremIpsumToolSetup extends AbstractToolSetup {

    private final JButton loremIpsumGenerateButton;
    private final JTextArea loremIpsumTextArea;

    public LoremIpsumToolSetup(JButton loremIpsumGenerateButton,
                               JTextArea loremIpsumTextArea) {
        this.loremIpsumGenerateButton = loremIpsumGenerateButton;
        this.loremIpsumTextArea = loremIpsumTextArea;
    }

    public void setup() {
        loremIpsumTextArea.setText(LoremIpsumTools.generateLoremIpsum(200));
        loremIpsumGenerateButton.addActionListener(e -> {
            loremIpsumTextArea.setText(LoremIpsumTools.generateLoremIpsum(200));
            loremIpsumTextArea.setCaretPosition(0);
        });
    }
}
