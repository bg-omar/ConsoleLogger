package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import static org.locationtech.jts.util.Debug.print;
import static org.locationtech.jts.util.Debug.println;

public class FunctionExtractorAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        print("----------------------------------------------");
        print("Line: 17 project: ");
        print("----------------------------------------------");
        Project project = e.getProject();

        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (project == null || editor == null) {
            return;
        }

        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile == null) {
            return;
        }

        PsiElement selectedElement = psiFile.findElementAt(editor.getCaretModel().getOffset());
        PsiMethod selectedMethod = PsiTreeUtil.getParentOfType(selectedElement, PsiMethod.class);

        if (selectedMethod != null) {
            // Start the extraction process
            String functionInfo = extractFunctionInfo(selectedMethod);
            displayResult(functionInfo, project);
        }
    }

    private String extractFunctionInfo(PsiMethod method) {
        StringBuilder result = new StringBuilder();

        // Function signature
        result.append("Function: ").append(method.getName()).append("\n\n");

        // Variables
        result.append("Variables:\n");
        PsiCodeBlock methodBody = method.getBody();
        if (methodBody != null) {
            for (PsiStatement statement : methodBody.getStatements()) {
                if (statement instanceof PsiDeclarationStatement) {
                    for (PsiElement declaredElement : ((PsiDeclarationStatement) statement).getDeclaredElements()) {
                        if (declaredElement instanceof PsiVariable) {
                            PsiVariable variable = (PsiVariable) declaredElement;
                            result.append(variable.getName())
                                    .append(" = ")
                                    .append(variable.getInitializer() != null ? variable.getInitializer().getText() : "unknown")
                                    .append("\n");
                        }
                    }
                }
            }
        }

        // Connected functions
        result.append("\nConnected Functions:\n");
        if (methodBody != null) {
            for (PsiStatement statement : methodBody.getStatements()) {
                if (statement instanceof PsiExpressionStatement) {
                    PsiExpression expression = ((PsiExpressionStatement) statement).getExpression();
                    if (expression instanceof PsiMethodCallExpression) {
                        PsiMethod calledMethod = ((PsiMethodCallExpression) expression).resolveMethod();
                        if (calledMethod != null) {
                            result.append(calledMethod.getName()).append("\n");
                        }
                    }
                }
            }
        }

        return result.toString();
    }

    private void displayResult(String functionInfo, Project project) {
        Messages.showInfoMessage(project, functionInfo, "Extracted Function Info");
    }
}