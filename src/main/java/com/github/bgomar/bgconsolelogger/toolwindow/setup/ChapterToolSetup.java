package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.chapters.Chapter;
import com.github.bgomar.bgconsolelogger.chapters.ChapterCollector;
import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class ChapterToolSetup  implements Disposable {

    private final Project project;
    private final DefaultListModel<String> chapterListModel;
    private final JList<String> chapterList;
    private static JTextField chapterTextField = new JTextField();

    public ChapterToolSetup(Project project, DefaultListModel<String> chapterListModel, JList<String> chapterList, JTextField chapterTextField) {
        this.project = project;
        this.chapterListModel = chapterListModel;
        this.chapterList = chapterList;
        this.chapterTextField = chapterTextField;
    }


    public void setup() {
        updateChapterList();

        // Initialize text field with the current CHAPTER_PATTERN value
        chapterTextField.setText(ConsoleLoggerSettings.getPattern(27));
        // ✅ Add a DocumentListener to refresh the chapter list on changes
        chapterTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePatternAndRefresh();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePatternAndRefresh();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePatternAndRefresh();
            }
        });


        // ✅ Add a Key Listener to detect changes
        chapterTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = chapterTextField.getText();
                ConsoleLoggerSettings.setPattern(27, newPattern);
                System.out.println("✍️ Updated CHAPTER_PATTERN: " + newPattern);
            }
        });

        // ✅ Handle file selection changes
        project.getMessageBus().connect().subscribe(
                FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
                    @Override
                    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                        System.out.println("📂 File opened: " + file.getName());
                        updateChapterList();
                    }

                    @Override
                    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                        VirtualFile newFile = event.getNewFile();
                        if (newFile != null) {
                            System.out.println("🔄 Switched to file: " + newFile.getName());
                            updateChapterList();
                        }
                    }
                }
        );

        // ✅ Handle clicks on the list items
        chapterList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // ✅ Double-click to navigate
                    String selectedTitle = chapterList.getSelectedValue();
                    if (selectedTitle != null) {
                        navigateToChapter(selectedTitle);
                    }
                }
            }
        });

        // ✅ Handle keyboard selection (Enter key)
        chapterList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String selectedTitle = chapterList.getSelectedValue();
                    if (selectedTitle != null) {
                        navigateToChapter(selectedTitle);
                    }
                }
            }
        });
    }

    // ✅ Helper method to update CHAPTER_PATTERN and refresh the list
    private void updatePatternAndRefresh() {
        String newPattern = chapterTextField.getText();
        ConsoleLoggerSettings.setPattern(27, newPattern);
        System.out.println("✍️ CHAPTER_PATTERN updated: " + newPattern);

        // ✅ Refresh chapter list dynamically
        updateChapterList();
    }

    private void navigateToChapter(String selectedTitle) {
        PsiFile file = getCurrentFile();
        if (file == null) {
            System.out.println("❌ No active file found.");
            return;
        }

        List<Chapter> chapters = ChapterCollector.collectChapters(file);
        for (Chapter chapter : chapters) {
            if (chapter.getTitle().equals(selectedTitle)) {
                navigateToLine(chapter.getLineNumber());
                break;
            }
        }
    }

    private void navigateToLine(int lineNumber) {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (editor != null) {
            editor.getCaretModel().moveToLogicalPosition(new LogicalPosition(lineNumber - 1, 0));
            editor.getScrollingModel().scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER);
            System.out.println("📌 Navigated to line: " + lineNumber);
        } else {
            System.out.println("❌ No active editor found.");
        }
    }

    @Override
    public void dispose() {
        // ✅ Cleanup resources when the plugin is disposed
        System.out.println("ChapterToolSetup disposed.");
    }

    public void updateChapterList() {
        System.out.println("🔍 Checking for chapters...");
        PsiFile file = getCurrentFile();
        System.out.print("file: "); System.out.println(file);

        if (file == null) {
            System.out.println("❌ No active file found. Waiting for a file to be opened.");
            return;
        }

        List<Chapter> chapters = ChapterCollector.collectChapters(file);
        System.out.print("📜 Chapters found: "); System.out.println(chapters);

        chapterListModel.clear();
        if (chapters.isEmpty()) {
            System.out.println("⚠️ No chapters detected in the current file.");
        } else {
            for (Chapter chapter : chapters) {
                chapterListModel.addElement(chapter.getTitle());
            }
        }

        // ✅ Ensure UI updates after the list changes
        SwingUtilities.invokeLater(() -> {
            chapterList.updateUI();
            chapterList.setSelectedIndex(0); // Optional: Auto-select first chapter
        });
    }


    private PsiFile getCurrentFile() {
        if (project == null) {
            System.out.println("❌ Project is null!");
            return null;
        }

        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        if (fileEditorManager == null) {
            System.out.println("❌ FileEditorManager is null!");
            return null;
        }

        Editor editor = fileEditorManager.getSelectedTextEditor();
        if (editor == null) {
            System.out.println("❌ No active editor found!");
            return null;
        }

        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile == null) {
            System.out.println("❌ No PSI file found for the current document.");
        } else {
            System.out.println("✅ Current file: " + psiFile.getName());
        }
        return psiFile;
    }


}