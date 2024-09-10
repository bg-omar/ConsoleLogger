package com.github.bgomar.consolelogger.toolwindow;

import com.github.bgomar.consolelogger.tools.ConsoleLoggerSettings;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.components.JBTextField;
import com.github.bgomar.consolelogger.toolwindow.setup.*;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ConsoleLoggerToolWindowPanel {

    private JPanel mainPanel;
    private JComboBox<ComboBoxWithImageItem> toolComboBox;
    private JLabel helpLabel;

    private JPanel urlCodecPanel;
    private JBTextField urlCodecDecodedTextArea;
    private JBTextField urlCodecEncodedTextArea;
    private JBTextField urlCodecSvg2CssTextArea;

    private JPanel px2RemPanel;
    private JBTextField px2RemTextField;
    private JBTextField rem2PxTextField;

    private JPanel propertiesConsoleLoggerPanel;
    private JTextField propertiesConsoleLoggerTextField1;
    private JTextField propertiesConsoleLoggerTextField2;
    private JTextField propertiesConsoleLoggerTextField3;
    private JTextField propertiesConsoleLoggerTextField4;
    private JTextField propertiesConsoleLoggerTextField5;
    private JTextField propertiesConsoleLoggerTextField6;
    private JTextField propertiesConsoleLoggerTextField7;
    private JTextField propertiesConsoleLoggerTextField8;
    private JTextField propertiesConsoleLoggerTextField9;
    private JTextField propertiesConsoleLoggerTextField10;
    private JTextField propertiesConsoleLoggerTextField11;
    private JTextField propertiesConsoleLoggerTextField12;
    private JTextField propertiesConsoleLoggerTextField13;
    private JTextField propertiesConsoleLoggerTextField14;
    private JTextField propertiesConsoleLoggerTextField15;
    private JTextField propertiesConsoleLoggerTextField16;
    private JTextField propertiesConsoleLoggerTextField17;
    private JTextField propertiesConsoleLoggerTextField18;
    private JTextField propertiesConsoleLoggerTextField19;
    private JTextField propertiesConsoleLoggerTextField20;
    private JTextField propertiesConsoleLoggerTextField21;
    private JTextField propertiesConsoleLoggerTextField22;
    private JTextField propertiesConsoleLoggerTextField23;
    private JTextField propertiesConsoleLoggerTextField24;
    private JTextField propertiesConsoleLoggerTextField25;
    private JTextField propertiesConsoleLoggerTextField26;
    private JTextField propertiesConsoleLoggerTextField27;
    private JButton saveAllButton;
    private JButton propertiesConsoleLoggerDefaultButton1;
    private JButton propertiesConsoleLoggerDefaultButton2;
    private JButton propertiesConsoleLoggerDefaultButton3;
    private JButton propertiesConsoleLoggerDefaultButton4;
    private JButton propertiesConsoleLoggerDefaultButton5;
    private JButton propertiesConsoleLoggerDefaultButton6;
    private JButton propertiesConsoleLoggerDefaultButton7;
    private JButton propertiesConsoleLoggerDefaultButton8;
    private JButton propertiesConsoleLoggerDefaultButton9;
    private JButton propertiesConsoleLoggerDefaultButton10;
    private JButton propertiesConsoleLoggerDefaultButton11;
    private JButton propertiesConsoleLoggerDefaultButton12;
    private JButton propertiesConsoleLoggerDefaultButton13;
    private JButton propertiesConsoleLoggerDefaultButton14;
    private JButton propertiesConsoleLoggerDefaultButton15;
    private JButton propertiesConsoleLoggerDefaultButton16;
    private JButton propertiesConsoleLoggerDefaultButton17;
    private JButton propertiesConsoleLoggerDefaultButton18;
    private JButton propertiesConsoleLoggerDefaultButton19;
    private JButton propertiesConsoleLoggerDefaultButton20;
    private JButton propertiesConsoleLoggerDefaultButton21;
    private JButton propertiesConsoleLoggerDefaultButton22;
    private JButton propertiesConsoleLoggerDefaultButton23;
    private JButton propertiesConsoleLoggerDefaultButton24;
    private JButton propertiesConsoleLoggerDefaultButton25;
    private JButton propertiesConsoleLoggerDefaultButton26;
    private JButton propertiesConsoleLoggerDefaultButton27;
    private JTextPane preview;
    private JButton cancelAllButton;
    private JButton preset1Button;
    private JButton preset2Button;
    private JButton preset3Button;


    private final LinkedHashMap<String, PanelAndIcon> toolPanelsByTitle = new LinkedHashMap<>();


    private record PanelAndIcon(JPanel panel, String icon) {
    }

    public ConsoleLoggerToolWindowPanel(ConsoleLoggerSettings settings) throws IOException {
        String iconsPath = "icons/";
        toolPanelsByTitle.put("Properties of ConsoleLogger", new PanelAndIcon(propertiesConsoleLoggerPanel, iconsPath + "cryingcatt.svg"));
        toolPanelsByTitle.put("Pixels to REM", new PanelAndIcon(px2RemPanel, iconsPath + "coolcat.svg"));
        toolPanelsByTitle.put("Svg 2 Css", new PanelAndIcon(urlCodecPanel, iconsPath + "devcat.svg"));


        new PropertiesConsoleLoggerToolSetup(
                propertiesConsoleLoggerTextField1,
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
                saveAllButton,
                cancelAllButton,
                preset1Button,
                preset2Button,
                preset3Button,
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
                propertiesConsoleLoggerDefaultButton27).setup();
        new URLCodecToolSetup(
            urlCodecDecodedTextArea,
            urlCodecEncodedTextArea,
            urlCodecSvg2CssTextArea,
            preview).setup();
        new Px2RemToolSetup(
            px2RemTextField,
            rem2PxTextField).setup();


        toolPanelsByTitle.forEach((title, panelAndIcon) -> toolComboBox.addItem(new ComboBoxWithImageItem(title, panelAndIcon.icon)));
        toolComboBox.setRenderer(new ComboBoxWithImageRenderer());
        toolComboBox.setMaximumRowCount(11);
        ComboboxSpeedSearch.installSpeedSearch(toolComboBox, ComboBoxWithImageItem::displayName);

        helpLabel.setText("");
        helpLabel.setIcon(IconLoader.getIcon(iconsPath + "contextHelp.svg", ConsoleLoggerToolWindowPanel.class));
        helpLabel.setToolTipText("");
        helpLabel.setVisible(false);

        toolComboBox.addActionListener(e -> {
            ComboBoxWithImageItem item = toolComboBox.getItemAt(toolComboBox.getSelectedIndex());
            displayToolPanel(item.title());

            helpLabel.setVisible(false);
            switch (item.title()) {
                case "Properties of ConsoleLogger" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                            "Quick change the console loggers.</html>");
                }
                case "Pixels to REM" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Change pixels to REM for css and it will<br>" +
                        "automatically converted as you type.</html>");
                }
                case "Svg 2 Css" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Create CSS from a svg file and it will<br>" +
                        "automatically converted as you type.</html>");
                }
            }
        });
        toolComboBox.setSelectedIndex(0);
    }

    private void displayToolPanel(String toolPanelTitle) {
        toolPanelsByTitle.forEach((s, jPanel) -> jPanel.panel().setVisible(false));
        toolPanelsByTitle.get(toolPanelTitle).panel().setVisible(true);
    }

    public JPanel getContent() {
        return mainPanel;
    }
}
