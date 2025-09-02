package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.github.bgomar.bgconsolelogger.chapters.Chapter;
import com.github.bgomar.bgconsolelogger.chapters.ChapterCollector;
import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings;
import com.github.bgomar.consolelogger.ChapterSettingsDialog;
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

import java.util.logging.Logger;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class ChapterToolSetup  implements Disposable {

    Logger logger = Logger.getLogger(getClass().getName());
    
    private final Project project;
    private final DefaultListModel<String> chapterListModel;
    private final JList<String> chapterList;
    private static JTextField chapterTextField = new JTextField();
    private final JTextField sectionTextField;
    private final JTextField subsectionTextField;
    private final JTextField chapterPatternNameTextField;
    private final JTextField sectionPatternNameTextField;
    private final JTextField subsectionPatternNameTextField;
    private final JButton chapterSettingsButton;


    public ChapterToolSetup(Project project, DefaultListModel<String> chapterListModel, JList<String> chapterList, JButton chapterSettingsButton, JTextField chapterTextField, JTextField sectionTextField, JTextField subsectionTextField, JTextField chapterPatternNameTextField, JTextField sectionPatternNameTextField, JTextField subsectionPatternNameTextField) {
        this.project = project;
        this.chapterListModel = chapterListModel;
        this.chapterList = chapterList;
        ChapterToolSetup.chapterTextField = chapterTextField;
        this.sectionTextField = sectionTextField;
        this.subsectionTextField = subsectionTextField;
        this.chapterPatternNameTextField = chapterPatternNameTextField;
        this.sectionPatternNameTextField = sectionPatternNameTextField;
        this.subsectionPatternNameTextField = subsectionPatternNameTextField;
        this.chapterSettingsButton = new JButton("Settings");
        // Add the button to the UI panel containing chapterPanel fields
        // Example: If you have a panel, add: panel.add(chapterSettingsButton);
    }


    public void setup() {
        updateChapterList();

        // Initialize text fields with their current pattern values
        chapterTextField.setText(ConsoleLoggerSettings.getPattern(27));
        sectionTextField.setText(ConsoleLoggerSettings.getPattern(28));
        subsectionTextField.setText(ConsoleLoggerSettings.getPattern(29));
        chapterPatternNameTextField.setText(ConsoleLoggerSettings.getPattern(30));
        sectionPatternNameTextField.setText(ConsoleLoggerSettings.getPattern(31));
        subsectionPatternNameTextField.setText(ConsoleLoggerSettings.getPattern(32));

        // Add DocumentListeners to refresh the chapter list on changes
        chapterTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updatePatternAndRefresh(27, chapterTextField); }
            @Override
            public void removeUpdate(DocumentEvent e) { updatePatternAndRefresh(27, chapterTextField); }
            @Override
            public void changedUpdate(DocumentEvent e) { updatePatternAndRefresh(27, chapterTextField); }
        });
        sectionTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updatePatternAndRefresh(28, sectionTextField); }
            @Override
            public void removeUpdate(DocumentEvent e) { updatePatternAndRefresh(28, sectionTextField); }
            @Override
            public void changedUpdate(DocumentEvent e) { updatePatternAndRefresh(28, sectionTextField); }
        });
        subsectionTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updatePatternAndRefresh(29, subsectionTextField); }
            @Override
            public void removeUpdate(DocumentEvent e) { updatePatternAndRefresh(29, subsectionTextField); }
            @Override
            public void changedUpdate(DocumentEvent e) { updatePatternAndRefresh(29, subsectionTextField); }
        });
        chapterPatternNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updatePatternAndRefresh(30, chapterPatternNameTextField); }
            @Override
            public void removeUpdate(DocumentEvent e) { updatePatternAndRefresh(30, chapterPatternNameTextField); }
            @Override
            public void changedUpdate(DocumentEvent e) { updatePatternAndRefresh(30, chapterPatternNameTextField); }
        });
        sectionPatternNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updatePatternAndRefresh(31, sectionPatternNameTextField); }
            @Override
            public void removeUpdate(DocumentEvent e) { updatePatternAndRefresh(31, sectionPatternNameTextField); }
            @Override
            public void changedUpdate(DocumentEvent e) { updatePatternAndRefresh(31, sectionPatternNameTextField); }
        });
        subsectionPatternNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updatePatternAndRefresh(32, subsectionPatternNameTextField); }
            @Override
            public void removeUpdate(DocumentEvent e) { updatePatternAndRefresh(32, subsectionPatternNameTextField); }
            @Override
            public void changedUpdate(DocumentEvent e) { updatePatternAndRefresh(32, subsectionPatternNameTextField); }
        });

        // Add KeyListeners to detect changes
        chapterTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = chapterTextField.getText();
                ConsoleLoggerSettings.setPattern(27, newPattern);
                logger.info("✍️ Updated CHAPTER_PATTERN: " + newPattern);
            }
        });
        sectionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = sectionTextField.getText();
                ConsoleLoggerSettings.setPattern(28, newPattern);
                logger.info("✍️ Updated SECTION_PATTERN: " + newPattern);
            }
        });
        subsectionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = subsectionTextField.getText();
                ConsoleLoggerSettings.setPattern(29, newPattern);
                logger.info("✍️ Updated SUBSECTION_PATTERN: " + newPattern);
            }
        });
        chapterPatternNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = chapterPatternNameTextField.getText(); // Ensure the method call matches the expected signature
                ConsoleLoggerSettings.setPattern(30, newPattern);
                logger.info("✍️ Updated CHAPTER_PATTERN_NAME: " + newPattern);
            }
        });
        sectionPatternNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = sectionPatternNameTextField.getText();
                ConsoleLoggerSettings.setPattern(31, newPattern);
                logger.info("✍️ Updated SECTION_PATTERN_NAME: " + newPattern);
            }
        });
        subsectionPatternNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPattern = subsectionPatternNameTextField.getText();
                ConsoleLoggerSettings.setPattern(32, newPattern);
                logger.info("✍️ Updated SUBSECTION_PATTERN_NAME: " + newPattern);
            }
        });

        // ✅ Handle file selection changes
        project.getMessageBus().connect().subscribe(
                FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
                    @Override
                    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                        logger.info("📂 File opened: " + file.getName());
                        updateChapterList();
                    }

                    @Override
                    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                        VirtualFile newFile = event.getNewFile();
                        if (newFile != null) {
                            logger.info("🔄 Switched to file: " + newFile.getName());
                            updateChapterList();
                        }
                    }

                    @Override
                    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                        // Handle file closed event if necessary
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

        // Add settings button event listener
        chapterSettingsButton.addActionListener(e -> {
            // Show the Kotlin dialog (ChapterSettingsDialog)
            ChapterSettingsDialog dialog = new ChapterSettingsDialog(
                chapterTextField.getText(),
                sectionTextField.getText(),
                subsectionTextField.getText(),
                chapterPatternNameTextField.getText(),
                sectionPatternNameTextField.getText(),
                subsectionPatternNameTextField.getText()
            );
            dialog.show();
            if (dialog.isOK()) {
                chapterTextField.setText(dialog.getChapter());
                sectionTextField.setText(dialog.getSection());
                subsectionTextField.setText(dialog.getSubsection());
                chapterPatternNameTextField.setText(dialog.getChapterPatternName());
                sectionPatternNameTextField.setText(dialog.getSectionPatternName());
                subsectionPatternNameTextField.setText(dialog.getSubsectionPatternName());
                ConsoleLoggerSettings.setPattern(27, dialog.getChapter());
                ConsoleLoggerSettings.setPattern(28, dialog.getSection());
                ConsoleLoggerSettings.setPattern(29, dialog.getSubsection());
                ConsoleLoggerSettings.setPattern(30, dialog.getChapterPatternName());
                ConsoleLoggerSettings.setPattern(31, dialog.getSectionPatternName());
                ConsoleLoggerSettings.setPattern(32, dialog.getSubsectionPatternName());
                updateChapterList();
            }
        });
    }

    // Helper method to update pattern and refresh the list
    private void updatePatternAndRefresh(int patternKey, JTextField field) {
        String newPattern = field.getText();
        ConsoleLoggerSettings.setPattern(patternKey, newPattern);

        // ✅ Refresh chapter list dynamically
        updateChapterList();
    }

    private void navigateToChapter(String selectedTitle) {
        PsiFile file = getCurrentFile();
        if (file == null) {
            logger.info("❌ No active file found.");
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
        } else {
            logger.info("❌ No active editor found.");
        }
    }

    @Override
    public void dispose() {
        // ✅ Cleanup resources when the plugin is disposed
        logger.info("ChapterToolSetup disposed.");
    }

    public void updateChapterList() {
        logger.info("🔍 Checking for chapters...");
        PsiFile file = getCurrentFile();

        if (file == null) {
            logger.info("❌ No active file found. Waiting for a file to be opened.");
            return;
        }

        List<Chapter> chapters = ChapterCollector.collectChapters(file);

        chapterListModel.clear();
        if (chapters.isEmpty()) {
            logger.info("⚠️ No chapters detected in the current file.");
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
            logger.info("❌ Project is null!");
            return null;
        }

        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        if (fileEditorManager == null) {
            logger.info("❌ FileEditorManager is null!");
            return null;
        }

        Editor editor = fileEditorManager.getSelectedTextEditor();
        if (editor == null) {
            logger.info("❌ No active editor found!");
            return null;
        }

        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile == null) {
            logger.info("❌ No PSI file found for the current document.");
        } else {
            logger.info("✅ Current file: " + psiFile.getName());
        }
        return psiFile;
    }


}