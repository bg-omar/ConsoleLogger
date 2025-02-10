package com.github.bgomar.bgconsolelogger.chapters;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChapterNavigationAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) return;

        // Get the current file
        PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(
                FileEditorManager.getInstance(project).getSelectedTextEditor().getDocument()
        );

        if (file == null) return;

        // Extract chapters
        List<Chapter> chapters = ChapterCollector.collectChapters(file);
        if (chapters.isEmpty()) return;

        // Navigate to the first chapter found (or modify to navigate to a selected one)
        int lineNumber = chapters.get(0).getLineNumber();
        navigateToLine(project, lineNumber);
    }

    private void navigateToLine(Project project, int lineNumber) {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (editor != null) {
            editor.getCaretModel().moveToLogicalPosition(new LogicalPosition(lineNumber - 1, 0));
            editor.getScrollingModel().scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER);
        }
    }
}