package com.github.bgomar.bgconsolelogger.tools;

import com.github.bgomar.bgconsolelogger.toolwindow.setup.PropertiesConsoleLoggerToolSetup;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;
import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerConfigurableUI;

import javax.swing.*;
import java.awt.*;

public class ConsoleLoggerConfigurableUI extends PropertiesConsoleLoggerToolSetup {
    public ConsoleLoggerConfigurableUI(JTextField propertiesConsoleLoggerTextField1, JTextField propertiesConsoleLoggerTextField2,
                                       JTextField propertiesConsoleLoggerTextField3, JTextField propertiesConsoleLoggerTextField4,
                                       JTextField propertiesConsoleLoggerTextField5, JTextField propertiesConsoleLoggerTextField6,
                                       JTextField propertiesConsoleLoggerTextField7, JTextField propertiesConsoleLoggerTextField8,
                                       JTextField propertiesConsoleLoggerTextField9, JButton propertiesConsoleLoggerbutton1) {
        super( propertiesConsoleLoggerTextField1, propertiesConsoleLoggerTextField2, propertiesConsoleLoggerTextField3,
                propertiesConsoleLoggerTextField4, propertiesConsoleLoggerTextField5, propertiesConsoleLoggerTextField6,
                propertiesConsoleLoggerTextField7, propertiesConsoleLoggerTextField8, propertiesConsoleLoggerTextField9,
                propertiesConsoleLoggerbutton1);
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

            for (int i = 1; i < 9; i++) {
                ui.add(new JTextField("(CTRL + ALT + " + i + ") "), gbc);
                gbc.gridx++;

                textFields[i] = new JBTextField();
                textFields[i].setText(ConsoleLoggerSettings.getPattern(i));
                textFields[i].setToolTipText("Tooltip for pattern " + i);
                ui.add(textFields[i], gbc);
                gbc.gridx++;

                JButton defaultButton = new JButton("Default");
                int finalIndex = i; // Need to make the index final for lambda expression
                defaultButton.addActionListener(e -> textFields[finalIndex].setText(ConsoleLoggerSettings.getPattern(finalIndex)));
                defaultButton.setToolTipText("Reset to default pattern");
                ui.add(defaultButton, gbc);
                gbc.gridx = 0; // Reset grid column for the next row
                gbc.gridy++;
            }
        }
    }
    @Override
    public void reset(@NotNull ConsoleLoggerSettings settings) {
        ui.reset(); }

    @Override
    public boolean isModified(@NotNull ConsoleLoggerSettings settings) { return ui.isModified(); }

    @Override
    public void apply(@NotNull ConsoleLoggerSettings settings) { ui.apply(); }

    @Override
    public @NotNull JComponent getComponent() { return ui; }
}

