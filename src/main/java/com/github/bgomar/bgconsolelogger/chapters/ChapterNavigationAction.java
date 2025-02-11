package com.github.bgomar.bgconsolelogger.chapters;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;

import java.util.List;
import java.util.Objects;

public class ChapterNavigationAction extends AnAction {

    public ChapterNavigationAction() {
        super("Navigate to Chapter"); // ✅ Required no-argument constructor
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) return;

        // ✅ Get the selected chapter from the UI
        JList<String> chapterList = (JList<String>) event.getData(DataKeys.CONTEXT_COMPONENT);
        if (!(chapterList instanceof JList<?>)) return;

        String selectedTitle = chapterList.getSelectedValue();
        if (selectedTitle == null) return;

        // ✅ Get the current file
        PsiFile file = PsiDocumentManager.getInstance(project)
                .getPsiFile(Objects.requireNonNull(FileEditorManager.getInstance(project)
                        .getSelectedTextEditor()).getDocument());

        if (file == null) return;

        // ✅ Extract chapters and find selected one
        List<Chapter> chapters = ChapterCollector.collectChapters(file);
        Chapter selectedChapter = chapters.stream()
                .filter(ch -> ch.getTitle().equals(selectedTitle))
                .findFirst()
                .orElse(null);

        if (selectedChapter != null) {
            navigateToLine(project, selectedChapter.getLineNumber()); // ✅ Now recognized
        }
    }

    private void navigateToLine(Project project, int lineNumber) {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (editor != null) {
            editor.getCaretModel().moveToLogicalPosition(new LogicalPosition(lineNumber - 1, 0));
            editor.getScrollingModel().scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER);
            System.out.println("📌 Navigated to line: " + lineNumber);
        } else {
            System.out.println("❌ No active editor found.");
        }
    }
}