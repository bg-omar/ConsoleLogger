package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.github.bgomar.bgconsolelogger.toolwindow.setup.ConfigPresetToolSetup;
import com.intellij.lang.javascript.psi.JSFunction;

import com.intellij.lang.javascript.psi.ecmal4.JSClass;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

public class FunctionExtractorAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (project == null || editor == null) {
            return;
        }

        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile == null) {
            return;
        }

        PsiElement selectedElement = psiFile.findElementAt(editor.getCaretModel().getOffset());

        // Try to extract a TypeScript function or class
        JSFunction selectedFunction = PsiTreeUtil.getParentOfType(selectedElement, JSFunction.class);
        JSClass selectedClass = PsiTreeUtil.getParentOfType(selectedElement, JSClass.class);

        if (selectedFunction != null) {
            // Function extraction logic
            String functionInfo = extractFunctionInfo(selectedFunction);
            displayResult(functionInfo);
        } else if (selectedClass != null) {
            // Class extraction logic
            String classInfo = extractClassInfo(selectedClass);
            displayResult(classInfo);
        } else {
            Messages.showInfoMessage(project, "No function or class selected.", "Error");
        }
    }

    private String extractFunctionInfo(JSFunction function) {
        StringBuilder result = new StringBuilder();
        result.append("Function: ").append(function.getName()).append("\n");

        // Parameters
        result.append("Parameters:\n");
        for (PsiElement param : function.getParameterList().getParameters()) {
            result.append(param.getText()).append("\n");
        }

        // Body
        result.append("\nFunction Body:\n");
        PsiElement body = function.getBlock();
        if (body != null) {
            result.append(body.getText()).append("\n");
        }

        return result.toString();
    }

    private String extractClassInfo(JSClass jsClass) {
        StringBuilder result = new StringBuilder();
        result.append("Class: ").append(jsClass.getName()).append("\n");

        // Class methods
        result.append("Methods:\n");
        for (JSFunction method : jsClass.getFunctions()) {
            result.append(method.getName()).append("\n");
        }

        return result.toString();
    }

    private void displayResult(String info) {
        ConfigPresetToolSetup.getFunctionExtractorTextArea().setText(info);
    }
}