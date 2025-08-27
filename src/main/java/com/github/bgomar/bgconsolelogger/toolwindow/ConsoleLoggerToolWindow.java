package com.github.bgomar.bgconsolelogger.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.ui.components.JBTextField;
import com.github.bgomar.bgconsolelogger.toolwindow.setup.*;

import javax.swing.*;
import java.util.LinkedHashMap;


public class ConsoleLoggerToolWindow {

    private JPanel mainPanel;
    private JComboBox<ComboBoxWithImageItem> toolComboBox;
    private JLabel helpLabel;

    private JPanel base64Panel;
    private JBRadioButton base64RadioButtonUTF8;
    private JBRadioButton base64RadioButtonASCII;
    private JTextArea base64RawTextArea;
    private JTextArea base64Base64TextArea;

    private JPanel px2RemPanel;
    private JBTextField px2RemTextField;
    private JBTextField rem2PxTextField;

    private JPanel svg2cssPanel;
    private JBTextField svg2cssEncodedTextArea;
    private JBTextField svg2cssDecodedTextArea;
    private JBTextField svg2CssTextArea;

    private JPanel chapterPanel;
    private JPanel chapterLinesPanel;
    private JList<String> chapterList;
    private JTextField chapterTextField;
    private JTextField  sectionTextField;
    private JTextField  subsectionTextField;
    private JTextField  chapterPatternNameTextField;
    private JTextField  sectionPatternNameTextField;
    private JTextField  subsectionPatternNameTextField;
    private JButton chapterSaveButton;
    private JButton chapterRefreshButton;

    // Properties of ConsoleLogger
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
    private JButton propertiesConsoleLoggerSaveButton;
    private JButton propertiesConsoleLoggerLoad2Button;
    private JButton propertiesConsoleLoggerLoad1Button;
    private JButton propertiesConsoleLoggerCancelButton;
    private JButton propertiesConsoleLoggerRecheckButton;

    private JButton propertiesConsoleLoggerDefaultButton1;
    private JButton propertiesConsoleLoggerDefaultButton2;
    private JButton propertiesConsoleLoggerDefaultButton3;
    private JButton propertiesConsoleLoggerDefaultButton4;
    private JButton propertiesConsoleLoggerDefaultButton5;
    private JButton propertiesConsoleLoggerDefaultButton6;
    private JButton propertiesConsoleLoggerDefaultButton7;
    private JButton propertiesConsoleLoggerDefaultButton8;
    private JButton propertiesConsoleLoggerDefaultButton9;

    private JPanel configPresetsPanel;
    private JButton functionExtractorClass;
    private JButton functionExtractorActionKT;
    private JButton functionExtractorAction;
    private JTextArea functionExtractorTextArea;
    private JButton saveActivateThisSetButton;

    private final LinkedHashMap<String, PanelAndIcon> toolPanelsByTitle = new LinkedHashMap<>();

    private record PanelAndIcon(JPanel panel, String icon) {
    }

    public ConsoleLoggerToolWindow(Project project) {
        DefaultListModel<String> chapterListModel = new DefaultListModel<>(); // ✅ Initialize list model
        this.chapterList.setModel(chapterListModel); // ✅ Set the model here

        String iconsPath = "icons/cats/";
        toolPanelsByTitle.put("Chapter", new PanelAndIcon(chapterPanel, iconsPath + "winecat.svg"));
        toolPanelsByTitle.put("Properties of ConsoleLogger ", new PanelAndIcon(propertiesConsoleLoggerPanel, iconsPath + "cryingcatt.svg"));
        toolPanelsByTitle.put("Obfuscate Classes", new PanelAndIcon(configPresetsPanel, iconsPath + "HackerPurr.svg"));
        toolPanelsByTitle.put("Pixels to REM", new PanelAndIcon(px2RemPanel, iconsPath + "cat1.svg"));
        toolPanelsByTitle.put("Svg 2 Css", new PanelAndIcon(svg2cssPanel, iconsPath + "coolcat.svg"));
        toolPanelsByTitle.put("Base64 encoder/decoder", new PanelAndIcon(base64Panel, iconsPath + "f03.svg"));



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
            propertiesConsoleLoggerDefaultButton9).setup();
        new ConfigPresetToolSetup(
            functionExtractorClass,
            functionExtractorActionKT,
            functionExtractorAction,
            functionExtractorTextArea).setup();
        new Base64ToolSetup(
            base64RadioButtonUTF8,
            base64RadioButtonASCII,
            base64RawTextArea,
            base64Base64TextArea).setup();
        new Svg2cssToolSetup(
            svg2cssEncodedTextArea,
            svg2cssDecodedTextArea,
            svg2CssTextArea).setup();
        new Px2RemToolSetup(
            px2RemTextField,
            rem2PxTextField).setup();
        new ChapterToolSetup(
            project,
            chapterLinesPanel,
            chapterListModel,
            chapterList,
            chapterTextField,
            sectionTextField,
            subsectionTextField,
            chapterPatternNameTextField,
            sectionPatternNameTextField,
            subsectionPatternNameTextField
        ).setup();



        toolPanelsByTitle.forEach((title, panelAndIcon) -> toolComboBox.addItem(new ComboBoxWithImageItem(title, panelAndIcon.icon)));
        toolComboBox.setRenderer(new ComboBoxWithImageRenderer());
        toolComboBox.setMaximumRowCount(11);
        ComboboxSpeedSearch.installSpeedSearch(toolComboBox, ComboBoxWithImageItem::displayName);

        helpLabel.setText("");
        helpLabel.setIcon(IconLoader.getIcon(iconsPath + "contextHelp.svg", ConsoleLoggerToolWindow.class));
        helpLabel.setToolTipText("");
        helpLabel.setVisible(false);

        toolComboBox.addActionListener(e -> {
            ComboBoxWithImageItem item = toolComboBox.getItemAt(toolComboBox.getSelectedIndex());
            displayToolPanel(item.title());

            helpLabel.setVisible(false);
            switch (item.title()) {
                case "Base64 encoder/decoder" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type some text or Base64 and it will be<br>" +
                        "automatically converted as you type.</html>");
                }
                case "URL encoder/decoder" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type decoded or encoded URL and it will be<br>" +
                        "automatically converted as you type.</html>");
                }
                case "Hash generator" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type text and various hash values will<br>" +
                        "be automatically computed as you type.</html>");
                }
                case "Chapter" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>Select a chapter to navigate within the editor.</html>");
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