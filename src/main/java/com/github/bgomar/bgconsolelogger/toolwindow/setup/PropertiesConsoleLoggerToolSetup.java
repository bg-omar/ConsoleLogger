package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings;
import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.ui.DialogPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class PropertiesConsoleLoggerToolSetup  extends AbstractToolSetup implements ConfigurableUi<ConsoleLoggerSettings> {
    private JTextField propertiesConsoleLoggerTextField1;
    private JTextField propertiesConsoleLoggerTextField2;
    private JTextField propertiesConsoleLoggerTextField3;
    private JTextField propertiesConsoleLoggerTextField4;
    private JTextField propertiesConsoleLoggerTextField5;
    private JTextField propertiesConsoleLoggerTextField6;
    private JTextField propertiesConsoleLoggerTextField7;
    private JTextField propertiesConsoleLoggerTextField8;
    private JTextField propertiesConsoleLoggerTextField9;
    private JButton propertiesConsoleLoggerbutton1;
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
                JButton propertiesConsoleLoggerbutton1) {
        this.propertiesConsoleLoggerTextField1 = propertiesConsoleLoggerTextField1;
        this.propertiesConsoleLoggerTextField2 = propertiesConsoleLoggerTextField2;
        this.propertiesConsoleLoggerTextField3 = propertiesConsoleLoggerTextField3;
        this.propertiesConsoleLoggerTextField4 = propertiesConsoleLoggerTextField4;
        this.propertiesConsoleLoggerTextField5 = propertiesConsoleLoggerTextField5;
        this.propertiesConsoleLoggerTextField6 = propertiesConsoleLoggerTextField6;
        this.propertiesConsoleLoggerTextField7 = propertiesConsoleLoggerTextField7;
        this.propertiesConsoleLoggerTextField8 = propertiesConsoleLoggerTextField8;
        this.propertiesConsoleLoggerTextField9 = propertiesConsoleLoggerTextField9;
        this.propertiesConsoleLoggerbutton1 = propertiesConsoleLoggerbutton1;
        setup();
    }

    public PropertiesConsoleLoggerToolSetup() {

    }


    public void setup() {
        this.propertiesConsoleLoggerTextField1  = new JTextField();
        this.propertiesConsoleLoggerTextField2  = new JTextField();
        this.propertiesConsoleLoggerTextField3  = new JTextField();
        this.propertiesConsoleLoggerTextField4  = new JTextField();
        this.propertiesConsoleLoggerTextField5  = new JTextField();
        this.propertiesConsoleLoggerTextField6  = new JTextField();
        this.propertiesConsoleLoggerTextField7  = new JTextField();
        this.propertiesConsoleLoggerTextField8  = new JTextField();
        this.propertiesConsoleLoggerTextField9  = new JTextField();
        this.propertiesConsoleLoggerbutton1     = new JButton();
        this.propertiesConsoleLoggerTextField9.setText(ConsoleLoggerSettings.getPattern(0));
        this.propertiesConsoleLoggerTextField1.setText(ConsoleLoggerSettings.getPattern(1));
        this.propertiesConsoleLoggerTextField2.setText(ConsoleLoggerSettings.getPattern(2));
        this.propertiesConsoleLoggerTextField3.setText(ConsoleLoggerSettings.getPattern(3));
        this.propertiesConsoleLoggerTextField4.setText(ConsoleLoggerSettings.getPattern(4));
        this.propertiesConsoleLoggerTextField5.setText(ConsoleLoggerSettings.getPattern(5));
        this.propertiesConsoleLoggerTextField6.setText(ConsoleLoggerSettings.getPattern(6));
        this.propertiesConsoleLoggerTextField7.setText(ConsoleLoggerSettings.getPattern(7));
        this.propertiesConsoleLoggerTextField8.setText(ConsoleLoggerSettings.getPattern(8));

        this.propertiesConsoleLoggerbutton1.addActionListener(e -> {
            ConsoleLoggerSettings.setPattern(1, propertiesConsoleLoggerTextField1.getText());
            ConsoleLoggerSettings.setPattern(2, propertiesConsoleLoggerTextField2.getText());
            ConsoleLoggerSettings.setPattern(3, propertiesConsoleLoggerTextField3.getText());
            ConsoleLoggerSettings.setPattern(4, propertiesConsoleLoggerTextField4.getText());
            ConsoleLoggerSettings.setPattern(5, propertiesConsoleLoggerTextField5.getText());
            ConsoleLoggerSettings.setPattern(6, propertiesConsoleLoggerTextField6.getText());
            ConsoleLoggerSettings.setPattern(7, propertiesConsoleLoggerTextField7.getText());
            ConsoleLoggerSettings.setPattern(8, propertiesConsoleLoggerTextField8.getText());
            ConsoleLoggerSettings.setPattern(0, propertiesConsoleLoggerTextField9.getText());
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
