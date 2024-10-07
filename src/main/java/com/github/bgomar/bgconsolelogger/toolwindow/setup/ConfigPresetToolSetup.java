package com.github.bgomar.bgconsolelogger.toolwindow.setup;


import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import javax.swing.*;

public class ConfigPresetToolSetup extends AbstractToolSetup {

    private static JButton uploadPresetButton = new JButton();
    private static JButton deployPresetButton = new JButton();
    private static JButton readPresetButton = new JButton();
    private static JButton editPresetButton = new JButton();
    private static JButton deletePresetButton = new JButton();

    private static JButton listConfigFilesButton = new JButton();

    private static JButton Button5 = new JButton();
    private static JButton Button4 = new JButton();
    private static JButton Button3 = new JButton();
    private static JButton Button2 = new JButton();
    private static JButton Button1 = new JButton();

    public ConfigPresetToolSetup(
                                 JButton uploadPresetButton,
                                 JButton deployPresetButton,
                                 JButton readPresetButton,
                                 JButton editPresetButton,
                                 JButton deletePresetButton,
                                 JButton listConfigFilesButton,
                                 JButton Button5,
                                 JButton Button4,
                                 JButton Button3,
                                 JButton Button2,
                                 JButton Button1) {

        ConfigPresetToolSetup.uploadPresetButton = uploadPresetButton;
        ConfigPresetToolSetup.deployPresetButton = deployPresetButton;
        ConfigPresetToolSetup.readPresetButton = readPresetButton;
        ConfigPresetToolSetup.editPresetButton = editPresetButton;
        ConfigPresetToolSetup.deletePresetButton = deletePresetButton;
        ConfigPresetToolSetup.listConfigFilesButton = listConfigFilesButton;
        ConfigPresetToolSetup.Button5 = Button5;
        ConfigPresetToolSetup.Button4 = Button4;
        ConfigPresetToolSetup.Button3 = Button3;
        ConfigPresetToolSetup.Button2 = Button2;
        ConfigPresetToolSetup.Button1 = Button1;
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
        Button1.addActionListener(e -> {
            AnAction uploadAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.UploadPresetAction");
            if (uploadAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                uploadAction.actionPerformed(actionEvent);  // Triggers the AnAction to open the config dialog
            }
        });

        // Add ActionListener for deploying presets
        Button2.addActionListener(e -> {
            AnAction deployAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.DeployPresetAction");
            if (deployAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                deployAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for reading presets
        Button3.addActionListener(e -> {
            AnAction readAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.ReadPresetAction");
            if (readAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                readAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for editing presets
        Button4.addActionListener(e -> {
            AnAction editAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.EditPresetAction");
            if (editAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                editAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for deleting presets
        Button5.addActionListener(e -> {
            AnAction deleteAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.DeletePresetAction");
            if (deleteAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                deleteAction.actionPerformed(actionEvent);
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
