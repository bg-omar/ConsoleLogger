package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import javax.swing.*;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionUiKind;

import java.awt.*;

public class ConfigPresetToolSetup extends AbstractToolSetup {

    private static JButton functionExtractorClass = new JButton();
    private static JButton functionExtractorActionKT = new JButton();
    private static JButton functionExtractorAction = new JButton();
    private static JTextArea functionExtractorTextArea = new JTextArea();

    public ConfigPresetToolSetup(
            JButton functionExtractorClass,
            JButton functionExtractorActionKT,
            JButton functionExtractorAction,
            JTextArea functionExtractorTextArea) {
        ConfigPresetToolSetup.functionExtractorClass = functionExtractorClass;
        ConfigPresetToolSetup.functionExtractorActionKT = functionExtractorActionKT;
        ConfigPresetToolSetup.functionExtractorAction = functionExtractorAction;
        ConfigPresetToolSetup.functionExtractorTextArea = functionExtractorTextArea;
    }

    public static JTextArea getFunctionExtractorTextArea() {
        return functionExtractorTextArea;
    }

    public static void setFunctionExtractorTextArea(JTextArea functionExtractorTextArea) {
        ConfigPresetToolSetup.functionExtractorTextArea = functionExtractorTextArea;
    }

    public void setup() {
        java.awt.Component parentComponent = functionExtractorActionKT;

        functionExtractorAction.addActionListener(e -> {
            AnAction listAction = ActionManager.getInstance().getAction("com.github.bgomar.consolelogger.FunctionExtractorAction");
            extractorActions(parentComponent, listAction);
        });

        functionExtractorActionKT.addActionListener(e -> {
            AnAction deployAction = ActionManager.getInstance().getAction("com.github.bgomar.bgconsolelogger.toolwindow.configfiles.FunctionExtractorActionKT");
            extractorActions(parentComponent, deployAction);
        });

        functionExtractorClass.addActionListener(e -> {
            AnAction readAction = ActionManager.getInstance().getAction("com.github.bgomar.bgconsolelogger.toolwindow.configfiles.FunctionExtractorClass");
            extractorActions(parentComponent, readAction);
        });
    }

    protected void extractorActions(Component parentComponent, AnAction listAction) {
        if (listAction != null) {
            DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);  // Pass the component
            Presentation presentation = new Presentation();
            AnActionEvent actionEvent = AnActionEvent.createEvent(dataContext, presentation, ActionPlaces.UNKNOWN, ActionUiKind.NONE, null);
            ActionManager.getInstance().tryToExecute(listAction, actionEvent.getInputEvent(), parentComponent, null, true);
        }
    }
}