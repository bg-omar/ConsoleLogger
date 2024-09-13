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
    private JTextPane preview;
    private JButton cancelAllButton;


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
                saveAllButton,
                cancelAllButton,
                propertiesConsoleLoggerDefaultButton1,
                propertiesConsoleLoggerDefaultButton2,
                propertiesConsoleLoggerDefaultButton3,
                propertiesConsoleLoggerDefaultButton4,
                propertiesConsoleLoggerDefaultButton5,
                propertiesConsoleLoggerDefaultButton6,
                propertiesConsoleLoggerDefaultButton7,
                propertiesConsoleLoggerDefaultButton8,
                propertiesConsoleLoggerDefaultButton9).setup();
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
