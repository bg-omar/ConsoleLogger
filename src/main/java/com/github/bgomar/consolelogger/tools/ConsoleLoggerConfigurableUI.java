package com.github.bgomar.consolelogger.tools;

import com.github.bgomar.consolelogger.toolwindow.setup.PropertiesConsoleLoggerToolSetup;
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
                                       JTextField propertiesConsoleLoggerTextField10,
                                       JTextField propertiesConsoleLoggerTextField11,
                                       JTextField propertiesConsoleLoggerTextField12,
                                       JTextField propertiesConsoleLoggerTextField13,
                                       JTextField propertiesConsoleLoggerTextField14,
                                       JTextField propertiesConsoleLoggerTextField15,
                                       JTextField propertiesConsoleLoggerTextField16,
                                       JTextField propertiesConsoleLoggerTextField17,
                                       JTextField propertiesConsoleLoggerTextField18,
                                       JTextField propertiesConsoleLoggerTextField19,
                                       JTextField propertiesConsoleLoggerTextField20,
                                       JTextField propertiesConsoleLoggerTextField21,
                                       JTextField propertiesConsoleLoggerTextField22,
                                       JTextField propertiesConsoleLoggerTextField23,
                                       JTextField propertiesConsoleLoggerTextField24,
                                       JTextField propertiesConsoleLoggerTextField25,
                                       JTextField propertiesConsoleLoggerTextField26,
                                       JTextField propertiesConsoleLoggerTextField27,
                                       JButton    propertiesConsoleLoggerSaveButton,
                                       JButton    propertiesConsoleLoggerCancelButton,
                                       JButton    propertiesConsoleLoggerDefaultButton1,
                                       JButton    propertiesConsoleLoggerDefaultButton2,
                                       JButton    propertiesConsoleLoggerDefaultButton3,
                                       JButton    propertiesConsoleLoggerDefaultButton4,
                                       JButton    propertiesConsoleLoggerDefaultButton5,
                                       JButton    propertiesConsoleLoggerDefaultButton6,
                                       JButton    propertiesConsoleLoggerDefaultButton7,
                                       JButton    propertiesConsoleLoggerDefaultButton8,
                                       JButton    propertiesConsoleLoggerDefaultButton9,
                                       JButton    propertiesConsoleLoggerDefaultButton10,
                                       JButton    propertiesConsoleLoggerDefaultButton11,
                                       JButton    propertiesConsoleLoggerDefaultButton12,
                                       JButton    propertiesConsoleLoggerDefaultButton13,
                                       JButton    propertiesConsoleLoggerDefaultButton14,
                                       JButton    propertiesConsoleLoggerDefaultButton15,
                                       JButton    propertiesConsoleLoggerDefaultButton16,
                                       JButton    propertiesConsoleLoggerDefaultButton17,
                                       JButton    propertiesConsoleLoggerDefaultButton18,
                                       JButton    propertiesConsoleLoggerDefaultButton19,
                                       JButton    propertiesConsoleLoggerDefaultButton20,
                                       JButton    propertiesConsoleLoggerDefaultButton21,
                                       JButton    propertiesConsoleLoggerDefaultButton22,
                                       JButton    propertiesConsoleLoggerDefaultButton23,
                                       JButton    propertiesConsoleLoggerDefaultButton24,
                                       JButton    propertiesConsoleLoggerDefaultButton25,
                                       JButton    propertiesConsoleLoggerDefaultButton26,
                                       JButton    propertiesConsoleLoggerDefaultButton27,
                                       JButton    preset1Button,
                                       JButton    preset2Button,
                                       JButton    preset3Button) {
        super(  propertiesConsoleLoggerTextField1,
                propertiesConsoleLoggerTextField2,
                propertiesConsoleLoggerTextField3,
                propertiesConsoleLoggerTextField4,
                propertiesConsoleLoggerTextField5,
                propertiesConsoleLoggerTextField6,
                propertiesConsoleLoggerTextField7,
                propertiesConsoleLoggerTextField8,
                propertiesConsoleLoggerTextField9,
                propertiesConsoleLoggerTextField10,
                propertiesConsoleLoggerTextField11,
                propertiesConsoleLoggerTextField12,
                propertiesConsoleLoggerTextField13,
                propertiesConsoleLoggerTextField14,
                propertiesConsoleLoggerTextField15,
                propertiesConsoleLoggerTextField16,
                propertiesConsoleLoggerTextField17,
                propertiesConsoleLoggerTextField18,
                propertiesConsoleLoggerTextField19,
                propertiesConsoleLoggerTextField20,
                propertiesConsoleLoggerTextField21,
                propertiesConsoleLoggerTextField22,
                propertiesConsoleLoggerTextField23,
                propertiesConsoleLoggerTextField24,
                propertiesConsoleLoggerTextField25,
                propertiesConsoleLoggerTextField26,
                propertiesConsoleLoggerTextField27,
                propertiesConsoleLoggerSaveButton,
                propertiesConsoleLoggerCancelButton,
                propertiesConsoleLoggerDefaultButton1,
                propertiesConsoleLoggerDefaultButton2,
                propertiesConsoleLoggerDefaultButton3,
                propertiesConsoleLoggerDefaultButton4,
                propertiesConsoleLoggerDefaultButton5,
                propertiesConsoleLoggerDefaultButton6,
                propertiesConsoleLoggerDefaultButton7,
                propertiesConsoleLoggerDefaultButton8,
                propertiesConsoleLoggerDefaultButton9,
                propertiesConsoleLoggerDefaultButton10,
                propertiesConsoleLoggerDefaultButton11,
                propertiesConsoleLoggerDefaultButton12,
                propertiesConsoleLoggerDefaultButton13,
                propertiesConsoleLoggerDefaultButton14,
                propertiesConsoleLoggerDefaultButton15,
                propertiesConsoleLoggerDefaultButton16,
                propertiesConsoleLoggerDefaultButton17,
                propertiesConsoleLoggerDefaultButton18,
                propertiesConsoleLoggerDefaultButton19,
                propertiesConsoleLoggerDefaultButton20,
                propertiesConsoleLoggerDefaultButton21,
                propertiesConsoleLoggerDefaultButton22,
                propertiesConsoleLoggerDefaultButton23,
                propertiesConsoleLoggerDefaultButton24,
                propertiesConsoleLoggerDefaultButton25,
                propertiesConsoleLoggerDefaultButton26,
                propertiesConsoleLoggerDefaultButton27,
                preset1Button,
                preset2Button,
                preset3Button);
    }

    public static class ConsoleLoggerConfig extends PropertiesConsoleLoggerToolSetup {
        public ConsoleLoggerConfig(@NotNull ConsoleLoggerSettings setting) {
            super();

            ui = new DialogPanel();
            ui.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            JBTextField[] textFields = new JBTextField[9];
            JButton[] defaultButton = new JButton[9];

            for (int i = 0; i < 9; i++) {
                ui.add(new JTextField("(CTRL + ALT + " + (i+1) + ") "), gbc);
                gbc.gridx++;

                textFields[i] = new JBTextField();
                textFields[i].setText(ConsoleLoggerSettings.getPattern(i));
                textFields[i].setToolTipText("Tooltip for pattern " + i+1);
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

        @Override
        public @NotNull JComponent getComponent() {
            return super.getComponent();
        }


    }
}
