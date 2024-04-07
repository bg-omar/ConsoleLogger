package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings;
import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.ui.DialogPanel;
import org.jetbrains.annotations.NotNull;


import javax.swing.*;

public class PropertiesConsoleLoggerToolSetup  extends AbstractToolSetup implements ConfigurableUi<ConsoleLoggerSettings> {
    private static JTextField propertiesConsoleLoggerTextField1 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField2 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField3 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField4 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField5 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField6 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField7 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField8 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField9 = new JTextField();
    private static JButton propertiesConsoleLoggerSaveButton = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton1 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton2 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton3 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton4 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton5 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton6 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton7 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton8 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton9 = new JButton();

    public static DialogPanel ui;

    public PropertiesConsoleLoggerToolSetup(
            JTextField propertiesConsoleLoggerTextField1,
            JTextField propertiesConsoleLoggerTextField2,
            JTextField propertiesConsoleLoggerTextField3,
            JTextField propertiesConsoleLoggerTextField4,
            JTextField propertiesConsoleLoggerTextField5,
            JTextField propertiesConsoleLoggerTextField6,
            JTextField propertiesConsoleLoggerTextField7,
            JTextField propertiesConsoleLoggerTextField8,
            JTextField propertiesConsoleLoggerTextField9,
            JButton propertiesConsoleLoggerSaveButton,
            JButton propertiesConsoleLoggerDefaultButton1,
            JButton propertiesConsoleLoggerDefaultButton2,
            JButton propertiesConsoleLoggerDefaultButton3,
            JButton propertiesConsoleLoggerDefaultButton4,
            JButton propertiesConsoleLoggerDefaultButton5,
            JButton propertiesConsoleLoggerDefaultButton6,
            JButton propertiesConsoleLoggerDefaultButton7,
            JButton propertiesConsoleLoggerDefaultButton8,
            JButton propertiesConsoleLoggerDefaultButton9
    ) {
        super();
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField1 = propertiesConsoleLoggerTextField1;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField2 = propertiesConsoleLoggerTextField2;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField3 = propertiesConsoleLoggerTextField3;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField4 = propertiesConsoleLoggerTextField4;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField5 = propertiesConsoleLoggerTextField5;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField6 = propertiesConsoleLoggerTextField6;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField7 = propertiesConsoleLoggerTextField7;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField8 = propertiesConsoleLoggerTextField8;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField9 = propertiesConsoleLoggerTextField9;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerSaveButton = propertiesConsoleLoggerSaveButton;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton1 = propertiesConsoleLoggerDefaultButton1;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton2 = propertiesConsoleLoggerDefaultButton2;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton3 = propertiesConsoleLoggerDefaultButton3;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton4 = propertiesConsoleLoggerDefaultButton4;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton5 = propertiesConsoleLoggerDefaultButton5;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton6 = propertiesConsoleLoggerDefaultButton6;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton7 = propertiesConsoleLoggerDefaultButton7;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton8 = propertiesConsoleLoggerDefaultButton8;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton9 = propertiesConsoleLoggerDefaultButton9;
    }

    public PropertiesConsoleLoggerToolSetup() {
        setup();
    }

    public void setup() {
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField1.setText(ConsoleLoggerSettings.getPattern(0));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField2.setText(ConsoleLoggerSettings.getPattern(1));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField3.setText(ConsoleLoggerSettings.getPattern(2));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField4.setText(ConsoleLoggerSettings.getPattern(3));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField5.setText(ConsoleLoggerSettings.getPattern(4));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField6.setText(ConsoleLoggerSettings.getPattern(5));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField7.setText(ConsoleLoggerSettings.getPattern(6));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField8.setText(ConsoleLoggerSettings.getPattern(7));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField9.setText(ConsoleLoggerSettings.getPattern(8));

        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton1.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField1.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_1));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton2.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField2.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_2));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton3.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField3.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_3));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton4.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField4.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_4));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton5.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField5.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_5));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton6.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField6.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_6));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton7.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField7.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_7));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton8.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField8.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_8));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton9.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField9.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_9));

        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerSaveButton.addActionListener(e -> {
            ConsoleLoggerSettings.setPattern(0, propertiesConsoleLoggerTextField1.getText());
            ConsoleLoggerSettings.setPattern(1, propertiesConsoleLoggerTextField2.getText());
            ConsoleLoggerSettings.setPattern(2, propertiesConsoleLoggerTextField3.getText());
            ConsoleLoggerSettings.setPattern(3, propertiesConsoleLoggerTextField4.getText());
            ConsoleLoggerSettings.setPattern(4, propertiesConsoleLoggerTextField5.getText());
            ConsoleLoggerSettings.setPattern(5, propertiesConsoleLoggerTextField6.getText());
            ConsoleLoggerSettings.setPattern(6, propertiesConsoleLoggerTextField7.getText());
            ConsoleLoggerSettings.setPattern(7, propertiesConsoleLoggerTextField8.getText());
            ConsoleLoggerSettings.setPattern(8, propertiesConsoleLoggerTextField9.getText());
        });
    }

    @Override
    public void reset(@NotNull ConsoleLoggerSettings settings) {
        ui.reset();
    }

    @Override
    public boolean isModified(@NotNull ConsoleLoggerSettings settings) {
        return ui.isModified();
    }

    @Override
    public void apply(@NotNull ConsoleLoggerSettings settings) {
        ui.apply();
    }

    @Override
    public @NotNull JComponent getComponent() {
        return ui;
    }
}
