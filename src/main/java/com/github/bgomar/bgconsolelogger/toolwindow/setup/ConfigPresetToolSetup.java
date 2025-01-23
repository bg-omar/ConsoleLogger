package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import javax.swing.*;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;

public class ConfigPresetToolSetup extends AbstractToolSetup {

    private static JButton functionExtractorClass = new JButton();
    private static JButton functionExtractorActionKT = new JButton();
    private static JButton functionExtractorAction = new JButton();

    public ConfigPresetToolSetup(
            JButton functionExtractorClass,
            JButton functionExtractorActionKT,
            JButton functionExtractorAction) {
        ConfigPresetToolSetup.functionExtractorClass = functionExtractorClass;
        ConfigPresetToolSetup.functionExtractorActionKT = functionExtractorActionKT;
        ConfigPresetToolSetup.functionExtractorAction = functionExtractorAction;
    }

    public void setup() {
        java.awt.Component parentComponent = functionExtractorActionKT;

        functionExtractorAction.addActionListener(e -> {
            AnAction listAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorAction");
            if (listAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                ActionManager.getInstance().tryToExecute(listAction, null, parentComponent, null, true);
            }
        });

        // Add ActionListener for deploying presets
        functionExtractorActionKT.addActionListener(e -> {
            AnAction deployAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorActionKT");
            if (deployAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                ActionManager.getInstance().tryToExecute(deployAction, null, parentComponent, null, true);
            }
        });

        // Add ActionListener for reading presets
        functionExtractorClass.addActionListener(e -> {
            AnAction readAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorClass");
            if (readAction != null) {
                DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
                AnActionEvent actionEvent = AnActionEvent.createFromDataContext("", new Presentation(), dataContext);
                ActionManager.getInstance().tryToExecute(readAction, null, parentComponent, null, true);
            }
        });
    }
}