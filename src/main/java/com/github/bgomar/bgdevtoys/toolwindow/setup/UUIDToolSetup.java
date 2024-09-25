package com.github.bgomar.bgdevtoys.toolwindow.setup;

import com.github.bgomar.bgdevtoys.tools.UUIDTools;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class UUIDToolSetup extends AbstractToolSetup {

    private final JButton uuidGenerateButton;
    private final JTextArea uuidTextArea;

    public UUIDToolSetup(JButton uuidGenerateButton,
                         JTextArea uuidTextArea) {
        this.uuidGenerateButton = uuidGenerateButton;
        this.uuidTextArea = uuidTextArea;
    }

    public void setup() {
        uuidTextArea.setText(UUIDTools.generateUUIDs(20));
        uuidGenerateButton.addActionListener(e -> {
            uuidTextArea.setText(UUIDTools.generateUUIDs(20));
            uuidTextArea.setCaretPosition(0);
        });
    }
}
