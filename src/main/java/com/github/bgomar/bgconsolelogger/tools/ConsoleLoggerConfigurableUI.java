package com.github.bgomar.bgconsolelogger.tools;

import com.github.bgomar.bgconsolelogger.toolwindow.setup.PropertiesConsoleLoggerToolSetup;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class ConsoleLoggerConfigurableUI extends PropertiesConsoleLoggerToolSetup {
    public ConsoleLoggerConfigurableUI(JTextField propertiesConsoleLoggerTextField1,
                                       JTextField propertiesConsoleLoggerTextField2,
                                       JTextField propertiesConsoleLoggerTextField3,
                                       JTextField propertiesConsoleLoggerTextField4,
                                       JTextField propertiesConsoleLoggerTextField5,
                                       JTextField propertiesConsoleLoggerTextField6,
                                       JTextField propertiesConsoleLoggerTextField7,
                                       JTextField propertiesConsoleLoggerTextField8,
                                       JTextField propertiesConsoleLoggerTextField9,
                                       JButton propertiesConsoleLoggerSaveButton,
                                       JButton propertiesConsoleLoggerLoad2Button,
                                       JButton propertiesConsoleLoggerLoad1Button,
                                       JButton propertiesConsoleLoggerCancelButton,
                                       JButton propertiesConsoleLoggerRecheckButton,
                                       JButton propertiesConsoleLoggerDefaultButton1,
                                       JButton propertiesConsoleLoggerDefaultButton2,
                                       JButton propertiesConsoleLoggerDefaultButton3,
                                       JButton propertiesConsoleLoggerDefaultButton4,
                                       JButton propertiesConsoleLoggerDefaultButton5,
                                       JButton propertiesConsoleLoggerDefaultButton6,
                                       JButton propertiesConsoleLoggerDefaultButton7,
                                       JButton propertiesConsoleLoggerDefaultButton8,
                                       JButton propertiesConsoleLoggerDefaultButton9) {
        super(  propertiesConsoleLoggerTextField1,
                propertiesConsoleLoggerTextField2,
                propertiesConsoleLoggerTextField3,
                propertiesConsoleLoggerTextField4,
                propertiesConsoleLoggerTextField5,
                propertiesConsoleLoggerTextField6,
                propertiesConsoleLoggerTextField7,
                propertiesConsoleLoggerTextField8,
                propertiesConsoleLoggerTextField9,
                propertiesConsoleLoggerSaveButton,
                propertiesConsoleLoggerLoad2Button,
                propertiesConsoleLoggerLoad1Button,
                propertiesConsoleLoggerCancelButton,
                propertiesConsoleLoggerRecheckButton,
                propertiesConsoleLoggerDefaultButton1,
                propertiesConsoleLoggerDefaultButton2,
                propertiesConsoleLoggerDefaultButton3,
                propertiesConsoleLoggerDefaultButton4,
                propertiesConsoleLoggerDefaultButton5,
                propertiesConsoleLoggerDefaultButton6,
                propertiesConsoleLoggerDefaultButton7,
                propertiesConsoleLoggerDefaultButton8,
                propertiesConsoleLoggerDefaultButton9);
    }

    public static class ConsoleLoggerConfig extends PropertiesConsoleLoggerToolSetup {
        public ConsoleLoggerConfig(@NotNull ConsoleLoggerSettings setting) {
            super();

            ui = new DialogPanel();
            ui.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            JBTextField[] textFields = new JBTextField[8];
            JButton[] defaultButton = new JButton[8];

            for (int i = 0; i < 8; i++) {
                int j = i + 1;
                ui.add(new JTextField("(CTRL + ALT + " + j + ") "), gbc);
                gbc.gridx++;

                textFields[i] = new JBTextField();
                textFields[i].setText(ConsoleLoggerSettings.getPattern(i));
                textFields[i].setToolTipText("Tooltip for pattern " + j);
                ui.add(textFields[i], gbc);
                gbc.gridx++;

                defaultButton[i] = new JButton("Default");
                int finalI = i;
                defaultButton[i].addActionListener(e -> textFields[finalI].setText(ConsoleLoggerSettings.getPattern(finalI)));
                defaultButton[i].setToolTipText("Reset to default pattern");
                ui.add(defaultButton[i], gbc);
                gbc.gridx = 0; // Reset grid column for the next row
                gbc.gridy++;
            }
        }


    }
}
