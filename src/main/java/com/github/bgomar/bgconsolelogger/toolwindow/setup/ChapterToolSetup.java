package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.chapters.Chapter;
import com.github.bgomar.bgconsolelogger.chapters.ChapterCollector;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.util.List;

public class ChapterToolSetup {
    private final Project project;
    private final DefaultListModel<String> chapterListModel;
    private final JList<String> chapterList;

    public ChapterToolSetup(Project project, DefaultListModel<String> chapterListModel, JList<String> chapterList) {
        this.project = project;
        this.chapterListModel = chapterListModel;
        this.chapterList = chapterList;
    }

    public void setup() {
        updateChapterList();
    }

    public void updateChapterList() {
        PsiFile file = getCurrentFile();
        if (file != null) {
            List<Chapter> chapters = ChapterCollector.collectChapters(file);
            chapterListModel.clear();
            for (Chapter chapter : chapters) {
                chapterListModel.addElement(chapter.getTitle());
            }
        }
    }

    private PsiFile getCurrentFile() {
        if (project == null) return null;  // Ensure project is available

        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project); // ✅ Pass project
        Editor editor = fileEditorManager.getSelectedTextEditor();
        if (editor == null) return null;

        return PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
    }
}