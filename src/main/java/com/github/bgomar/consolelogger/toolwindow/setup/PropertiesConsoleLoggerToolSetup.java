package com.github.bgomar.consolelogger.toolwindow.setup;

import com.github.bgomar.consolelogger.tools.ConsoleLoggerSettings;
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
    private static JTextField propertiesConsoleLoggerTextField10 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField11 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField12 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField13 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField14 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField15 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField16 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField17 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField18 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField19 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField20 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField21 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField22 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField23 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField24 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField25 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField26 = new JTextField();
    private static JTextField propertiesConsoleLoggerTextField27 = new JTextField();

    private static JButton propertiesConsoleLoggerSaveButton = new JButton();
    private static JButton propertiesConsoleLoggerCancelButton = new JButton();

    private static JButton propertiesConsoleLoggerDefaultButton1 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton2 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton3 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton4 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton5 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton6 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton7 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton8 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton9 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton10 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton11 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton12 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton13 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton14 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton15 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton16 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton17 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton18 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton19 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton20 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton21 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton22 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton23 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton24 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton25 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton26 = new JButton();
    private static JButton propertiesConsoleLoggerDefaultButton27 = new JButton();

    public static DialogPanel ui = new DialogPanel();
    private static JButton preset1Button = new JButton();
    private static JButton preset2Button = new JButton();
    private static JButton preset3Button = new JButton();

    public PropertiesConsoleLoggerToolSetup() {
        setup();
    }

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
            JButton propertiesConsoleLoggerSaveButton,
            JButton propertiesConsoleLoggerCancelButton,
            JButton propertiesConsoleLoggerDefaultButton1,
            JButton propertiesConsoleLoggerDefaultButton2,
            JButton propertiesConsoleLoggerDefaultButton3,
            JButton propertiesConsoleLoggerDefaultButton4,
            JButton propertiesConsoleLoggerDefaultButton5,
            JButton propertiesConsoleLoggerDefaultButton6,
            JButton propertiesConsoleLoggerDefaultButton7,
            JButton propertiesConsoleLoggerDefaultButton8,
            JButton propertiesConsoleLoggerDefaultButton9,
            JButton propertiesConsoleLoggerDefaultButton10,
            JButton propertiesConsoleLoggerDefaultButton11,
            JButton propertiesConsoleLoggerDefaultButton12,
            JButton propertiesConsoleLoggerDefaultButton13,
            JButton propertiesConsoleLoggerDefaultButton14,
            JButton propertiesConsoleLoggerDefaultButton15,
            JButton propertiesConsoleLoggerDefaultButton16,
            JButton propertiesConsoleLoggerDefaultButton17,
            JButton propertiesConsoleLoggerDefaultButton18,
            JButton propertiesConsoleLoggerDefaultButton19,
            JButton propertiesConsoleLoggerDefaultButton20,
            JButton propertiesConsoleLoggerDefaultButton21,
            JButton propertiesConsoleLoggerDefaultButton22,
            JButton propertiesConsoleLoggerDefaultButton23,
            JButton propertiesConsoleLoggerDefaultButton24,
            JButton propertiesConsoleLoggerDefaultButton25,
            JButton propertiesConsoleLoggerDefaultButton26,
            JButton propertiesConsoleLoggerDefaultButton27,
            JButton preset1Button,
            JButton preset2Button,
            JButton preset3Button
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
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField10 = propertiesConsoleLoggerTextField10;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField11 = propertiesConsoleLoggerTextField11;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField12 = propertiesConsoleLoggerTextField12;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField13 = propertiesConsoleLoggerTextField13;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField14 = propertiesConsoleLoggerTextField14;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField15 = propertiesConsoleLoggerTextField15;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField16 = propertiesConsoleLoggerTextField16;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField17 = propertiesConsoleLoggerTextField17;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField18 = propertiesConsoleLoggerTextField18;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField19 = propertiesConsoleLoggerTextField19;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField20 = propertiesConsoleLoggerTextField20;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField21 = propertiesConsoleLoggerTextField21;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField22 = propertiesConsoleLoggerTextField22;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField23 = propertiesConsoleLoggerTextField23;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField24 = propertiesConsoleLoggerTextField24;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField25 = propertiesConsoleLoggerTextField25;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField26 = propertiesConsoleLoggerTextField26;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField27 = propertiesConsoleLoggerTextField27;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerSaveButton = propertiesConsoleLoggerSaveButton;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerCancelButton = propertiesConsoleLoggerCancelButton;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton1 = propertiesConsoleLoggerDefaultButton1;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton2 = propertiesConsoleLoggerDefaultButton2;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton3 = propertiesConsoleLoggerDefaultButton3;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton4 = propertiesConsoleLoggerDefaultButton4;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton5 = propertiesConsoleLoggerDefaultButton5;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton6 = propertiesConsoleLoggerDefaultButton6;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton7 = propertiesConsoleLoggerDefaultButton7;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton8 = propertiesConsoleLoggerDefaultButton8;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton9 = propertiesConsoleLoggerDefaultButton9;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton10 = propertiesConsoleLoggerDefaultButton10;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton11 = propertiesConsoleLoggerDefaultButton11;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton12 = propertiesConsoleLoggerDefaultButton12;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton13 = propertiesConsoleLoggerDefaultButton13;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton14 = propertiesConsoleLoggerDefaultButton14;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton15 = propertiesConsoleLoggerDefaultButton15;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton16 = propertiesConsoleLoggerDefaultButton16;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton17 = propertiesConsoleLoggerDefaultButton17;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton18 = propertiesConsoleLoggerDefaultButton18;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton19 = propertiesConsoleLoggerDefaultButton19;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton20 = propertiesConsoleLoggerDefaultButton20;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton21 = propertiesConsoleLoggerDefaultButton21;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton22 = propertiesConsoleLoggerDefaultButton22;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton23 = propertiesConsoleLoggerDefaultButton23;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton24 = propertiesConsoleLoggerDefaultButton24;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton25 = propertiesConsoleLoggerDefaultButton25;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton26 = propertiesConsoleLoggerDefaultButton26;
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton27 = propertiesConsoleLoggerDefaultButton27;
        PropertiesConsoleLoggerToolSetup.preset1Button = preset1Button;
        PropertiesConsoleLoggerToolSetup.preset2Button = preset2Button;
        PropertiesConsoleLoggerToolSetup.preset3Button = preset3Button;
    }

    public void setup() {
        loadAllLoggers();

        PropertiesConsoleLoggerToolSetup.preset1Button.addActionListener(e -> {
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField1.setText(ConsoleLoggerSettings.getPattern(0));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField2.setText(ConsoleLoggerSettings.getPattern(1));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField3.setText(ConsoleLoggerSettings.getPattern(2));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField4.setText(ConsoleLoggerSettings.getPattern(3));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField5.setText(ConsoleLoggerSettings.getPattern(4));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField6.setText(ConsoleLoggerSettings.getPattern(5));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField7.setText(ConsoleLoggerSettings.getPattern(6));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField8.setText(ConsoleLoggerSettings.getPattern(7));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField9.setText(ConsoleLoggerSettings.getPattern(8));
        });

        PropertiesConsoleLoggerToolSetup.preset2Button.addActionListener(e -> {
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField10.setText(ConsoleLoggerSettings.getPattern(9));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField11.setText(ConsoleLoggerSettings.getPattern(10));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField12.setText(ConsoleLoggerSettings.getPattern(11));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField13.setText(ConsoleLoggerSettings.getPattern(12));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField14.setText(ConsoleLoggerSettings.getPattern(13));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField15.setText(ConsoleLoggerSettings.getPattern(14));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField16.setText(ConsoleLoggerSettings.getPattern(15));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField17.setText(ConsoleLoggerSettings.getPattern(16));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField18.setText(ConsoleLoggerSettings.getPattern(17));

        });

        PropertiesConsoleLoggerToolSetup.preset3Button.addActionListener(e -> {
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField19.setText(ConsoleLoggerSettings.getPattern(18));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField20.setText(ConsoleLoggerSettings.getPattern(19));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField21.setText(ConsoleLoggerSettings.getPattern(20));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField22.setText(ConsoleLoggerSettings.getPattern(21));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField23.setText(ConsoleLoggerSettings.getPattern(22));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField24.setText(ConsoleLoggerSettings.getPattern(23));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField25.setText(ConsoleLoggerSettings.getPattern(24));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField26.setText(ConsoleLoggerSettings.getPattern(25));
            PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField27.setText(ConsoleLoggerSettings.getPattern(26));
        });

        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton1.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField1.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_1));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton2.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField2.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_2));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton3.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField3.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_3));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton4.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField4.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_4));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton5.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField5.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_5));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton6.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField6.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_6));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton7.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField7.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_7));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton8.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField8.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_8));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton9.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField9.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_9));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton10.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField10.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_10));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton11.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField11.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_11));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton12.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField12.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_12));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton13.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField13.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_13));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton14.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField14.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_14));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton15.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField15.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_15));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton16.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField16.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_16));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton17.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField17.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_17));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton18.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField18.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_18));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton19.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField19.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_19));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton20.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField20.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_20));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton21.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField21.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_21));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton22.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField22.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_22));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton23.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField23.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_23));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton24.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField24.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_24));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton25.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField25.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_25));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton26.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField26.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_26));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerDefaultButton27.addActionListener(e -> PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField27.setText(ConsoleLoggerSettings.DEFAULT_PATTERN_27));

        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerCancelButton.addActionListener(e -> {
            loadAllLoggers();
        });

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
            ConsoleLoggerSettings.setPattern(9, propertiesConsoleLoggerTextField10.getText());
            ConsoleLoggerSettings.setPattern(10, propertiesConsoleLoggerTextField11.getText());
            ConsoleLoggerSettings.setPattern(11, propertiesConsoleLoggerTextField12.getText());
            ConsoleLoggerSettings.setPattern(12, propertiesConsoleLoggerTextField13.getText());
            ConsoleLoggerSettings.setPattern(13, propertiesConsoleLoggerTextField14.getText());
            ConsoleLoggerSettings.setPattern(14, propertiesConsoleLoggerTextField15.getText());
            ConsoleLoggerSettings.setPattern(15, propertiesConsoleLoggerTextField16.getText());
            ConsoleLoggerSettings.setPattern(16, propertiesConsoleLoggerTextField17.getText());
            ConsoleLoggerSettings.setPattern(17, propertiesConsoleLoggerTextField18.getText());
            ConsoleLoggerSettings.setPattern(18, propertiesConsoleLoggerTextField19.getText());
            ConsoleLoggerSettings.setPattern(19, propertiesConsoleLoggerTextField20.getText());
            ConsoleLoggerSettings.setPattern(20, propertiesConsoleLoggerTextField21.getText());
            ConsoleLoggerSettings.setPattern(21, propertiesConsoleLoggerTextField22.getText());
            ConsoleLoggerSettings.setPattern(22, propertiesConsoleLoggerTextField23.getText());
            ConsoleLoggerSettings.setPattern(23, propertiesConsoleLoggerTextField24.getText());
            ConsoleLoggerSettings.setPattern(24, propertiesConsoleLoggerTextField25.getText());
            ConsoleLoggerSettings.setPattern(25, propertiesConsoleLoggerTextField26.getText());
            ConsoleLoggerSettings.setPattern(26, propertiesConsoleLoggerTextField27.getText());
        });
    }

    private void loadAllLoggers() {
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField1.setText(ConsoleLoggerSettings.getPattern(0));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField2.setText(ConsoleLoggerSettings.getPattern(1));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField3.setText(ConsoleLoggerSettings.getPattern(2));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField4.setText(ConsoleLoggerSettings.getPattern(3));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField5.setText(ConsoleLoggerSettings.getPattern(4));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField6.setText(ConsoleLoggerSettings.getPattern(5));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField7.setText(ConsoleLoggerSettings.getPattern(6));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField8.setText(ConsoleLoggerSettings.getPattern(7));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField9.setText(ConsoleLoggerSettings.getPattern(8));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField10.setText(ConsoleLoggerSettings.getPattern(9));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField11.setText(ConsoleLoggerSettings.getPattern(10));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField12.setText(ConsoleLoggerSettings.getPattern(11));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField13.setText(ConsoleLoggerSettings.getPattern(12));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField14.setText(ConsoleLoggerSettings.getPattern(13));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField15.setText(ConsoleLoggerSettings.getPattern(14));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField16.setText(ConsoleLoggerSettings.getPattern(15));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField17.setText(ConsoleLoggerSettings.getPattern(16));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField18.setText(ConsoleLoggerSettings.getPattern(17));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField19.setText(ConsoleLoggerSettings.getPattern(18));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField20.setText(ConsoleLoggerSettings.getPattern(19));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField21.setText(ConsoleLoggerSettings.getPattern(20));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField22.setText(ConsoleLoggerSettings.getPattern(21));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField23.setText(ConsoleLoggerSettings.getPattern(22));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField24.setText(ConsoleLoggerSettings.getPattern(23));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField25.setText(ConsoleLoggerSettings.getPattern(24));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField26.setText(ConsoleLoggerSettings.getPattern(25));
        PropertiesConsoleLoggerToolSetup.propertiesConsoleLoggerTextField27.setText(ConsoleLoggerSettings.getPattern(26));
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
