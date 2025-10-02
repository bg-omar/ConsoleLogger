package com.github.bgomar.bgconsolelogger.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ComboboxSpeedSearch;
import com.github.bgomar.bgconsolelogger.toolwindow.setup.*;
import com.github.bgomar.bgconsolelogger.toolwindow.components.ChapterJBList;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.util.LinkedHashMap;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;


public class ConsoleLoggerToolWindow {

    private JPanel mainPanel;
    private JComboBox<ComboBoxWithImageItem> toolComboBox;
    private JLabel helpLabel;

    private JPanel chapterPanel;
    private JPanel chapterLinesPanel; // retained (UI Designer field)
    private JList<String> chapterList; // UI Designer injected
    private JTextField chapterTextField;
    private JTextField sectionTextField;
    private JTextField subsectionTextField;
    private JTextField chapterPatternNameTextField;
    private JTextField sectionPatternNameTextField;
    private JTextField subsectionPatternNameTextField;
    private JButton chapterSettingsButton; // UI Designer injected


    // Properties of ConsoleLogger (UI Designer fields)
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
    private JButton propertiesConsoleLoggerRemoveButton;
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
    private JScrollBar scrollBar1; // UI Designer field


    private final LinkedHashMap<String, PanelAndIcon> toolPanelsByTitle = new LinkedHashMap<>();

    private record PanelAndIcon(JPanel panel, String icon) { }

    public ConsoleLoggerToolWindow(Project project) {
        DefaultListModel<String> chapterListModel = new DefaultListModel<>();

        // Install custom list BEFORE listeners
        installChapterCustomList(chapterListModel);

        String iconsPath = "icons/cats/";
        toolPanelsByTitle.put("Properties", new PanelAndIcon(propertiesConsoleLoggerPanel, iconsPath + "cryingcatt.svg"));
        toolPanelsByTitle.put("Chapters", new PanelAndIcon(chapterPanel, iconsPath + "winecat.svg"));

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
            propertiesConsoleLoggerRemoveButton,
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

        new ChapterToolSetup(
            project,
            chapterListModel,
            chapterList,
            chapterSettingsButton,
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
                case "Properties" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>Set the ConsoleLoggers</html>");
                }
                case "Chapters" -> {
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

    // Replace the Designer JList with ChapterJBList wrapped in JBScrollPane
    private void installChapterCustomList(DefaultListModel<String> model) {
        if (chapterList instanceof ChapterJBList && SwingUtilities.getAncestorOfClass(JScrollPane.class, chapterList) != null) {
            chapterList.setModel(model);
            return;
        }
        if (chapterList == null) return;
        java.awt.Container parent = chapterList.getParent();
        if (parent == null) {
            SwingUtilities.invokeLater(() -> installChapterCustomList(model));
            return;
        }
        ChapterJBList replacement = new ChapterJBList();
        replacement.setModel(model);
        replacement.setSelectionMode(chapterList.getSelectionMode());
        replacement.setLayoutOrientation(chapterList.getLayoutOrientation());
        replacement.setVisibleRowCount(-1);

        JBScrollPane scrollPane = new JBScrollPane(replacement, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        int idx = -1;
        for (int i = 0; i < parent.getComponentCount(); i++) {
            if (parent.getComponent(i) == chapterList) { idx = i; break; }
        }
        if (idx >= 0) {
            parent.remove(idx);
            parent.add(scrollPane, idx);
            parent.revalidate();
            parent.repaint();
            // Suppress inspection warning: we intentionally replace the UI-bound field with enhanced component
            // noinspection AssignmentToStaticFieldFromInstanceMethod,AssignmentToNullField
            chapterList = replacement;
        }
    }

    public JPanel getContent() { return mainPanel; }
}