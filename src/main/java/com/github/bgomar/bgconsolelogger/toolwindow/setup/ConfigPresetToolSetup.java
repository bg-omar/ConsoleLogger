package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.tools.px2RemTool;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.ui.components.JBTextField;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ConfigPresetToolSetup extends AbstractToolSetup {

    private static JButton uploadPresetButton = new JButton();
    private static JButton deployPresetButton = new JButton();
    private static JButton readPresetButton = new JButton();
    private static JButton editPresetButton = new JButton();
    private static JButton deletePresetButton = new JButton();

    private static JButton listConfigFilesButton = new JButton();

    public ConfigPresetToolSetup(
                                 JButton uploadPresetButton,
                                 JButton deployPresetButton,
                                 JButton readPresetButton,
                                 JButton editPresetButton,
                                 JButton deletePresetButton,
                                 JButton listConfigFilesButton) {

        ConfigPresetToolSetup.uploadPresetButton = uploadPresetButton;
        ConfigPresetToolSetup.deployPresetButton = deployPresetButton;
        ConfigPresetToolSetup.readPresetButton = readPresetButton;
        ConfigPresetToolSetup.editPresetButton = editPresetButton;
        ConfigPresetToolSetup.deletePresetButton = deletePresetButton;
        ConfigPresetToolSetup.listConfigFilesButton = listConfigFilesButton;
    }

    public void setup() {
        // Use a known AWT component like px2RemTextField or one of the buttons
        java.awt.Component parentComponent = uploadPresetButton;


        listConfigFilesButton.addActionListener(e -> {
            AnAction listAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.ListConfigFilesAction");
            if (listAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                listAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for uploading presets via AnAction
        uploadPresetButton.addActionListener(e -> {
            AnAction uploadAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.UploadPresetAction");
            if (uploadAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                uploadAction.actionPerformed(actionEvent);  // Triggers the AnAction to open the config dialog
            }
        });

        // Add ActionListener for deploying presets
        deployPresetButton.addActionListener(e -> {
            AnAction deployAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.DeployPresetAction");
            if (deployAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                deployAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for reading presets
        readPresetButton.addActionListener(e -> {
            AnAction readAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.ReadPresetAction");
            if (readAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                readAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for editing presets
        editPresetButton.addActionListener(e -> {
            AnAction editAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.EditPresetAction");
            if (editAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                editAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for deleting presets
        deletePresetButton.addActionListener(e -> {
            AnAction deleteAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.DeletePresetAction");
            if (deleteAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                deleteAction.actionPerformed(actionEvent);
            }
        });

    }
}
