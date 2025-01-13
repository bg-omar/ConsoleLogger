package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.intellij.largeFilesEditor.file.FileChangeListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.*;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;

import javax.swing.*;

public class ConfigPresetToolSetup extends AbstractToolSetup {

/*    private static JButton uploadPresetButton = new JButton();
    private static JButton deployPresetButton = new JButton();
    private static JButton readPresetButton = new JButton();
    private static JButton editPresetButton = new JButton();
    private static JButton deletePresetButton = new JButton();

    private static JButton listConfigFilesButton = new JButton();

    private static JButton Button5 = new JButton();
    private static JButton Button4 = new JButton();*/
    private static JButton functionExtractorClass = new JButton();
    private static JButton functionExtractorActionKT = new JButton();
    private static JButton functionExtractorAction = new JButton();

    public ConfigPresetToolSetup(
//                                 JButton uploadPresetButton,
//                                 JButton deployPresetButton,
//                                 JButton readPresetButton,
//                                 JButton editPresetButton,
//                                 JButton deletePresetButton,
//                                 JButton listConfigFilesButton,
//                                 JButton Button5,
//                                 JButton Button4,
                                 JButton functionExtractorClass,
                                 JButton functionExtractorActionKT,
                                 JButton functionExtractorAction) {

/*        ConfigPresetToolSetup.uploadPresetButton = uploadPresetButton;
        ConfigPresetToolSetup.deployPresetButton = deployPresetButton;
        ConfigPresetToolSetup.readPresetButton = readPresetButton;
        ConfigPresetToolSetup.editPresetButton = editPresetButton;
        ConfigPresetToolSetup.deletePresetButton = deletePresetButton;
        ConfigPresetToolSetup.listConfigFilesButton = listConfigFilesButton;
        ConfigPresetToolSetup.Button5 = Button5;
        ConfigPresetToolSetup.Button4 = Button4;*/
        ConfigPresetToolSetup.functionExtractorClass = functionExtractorClass;
        ConfigPresetToolSetup.functionExtractorActionKT = functionExtractorActionKT;
        ConfigPresetToolSetup.functionExtractorAction = functionExtractorAction;
    }

    public void setup() {
        // Use a known AWT component like px2RemTextField or one of the buttons
        java.awt.Component parentComponent = functionExtractorActionKT;

        functionExtractorAction.addActionListener(e -> {
            AnAction listAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorAction");
            if (listAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                listAction.actionPerformed(actionEvent);
            }

        });

        // Add ActionListener for deploying presets
        functionExtractorActionKT.addActionListener(e -> {
            AnAction deployAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorActionKT");
            if (deployAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                deployAction.actionPerformed(actionEvent);
            }
        });

        // Add ActionListener for reading presets
        functionExtractorClass.addActionListener(e -> {
            AnAction readAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorClass");
            if (readAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                readAction.actionPerformed(actionEvent);
            }
        });

      /*
       listConfigFilesButton.addActionListener(e -> {
            AnAction listAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.ListConfigFilesAction");
            if (listAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                listAction.actionPerformed(actionEvent);
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
        });*/

    }
}