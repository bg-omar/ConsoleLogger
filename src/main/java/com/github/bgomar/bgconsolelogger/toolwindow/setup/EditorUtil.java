package com.github.bgomar.bgconsolelogger.toolwindow.setup;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

public class EditorUtil {

    public static Editor getLastUsedEditor(Project project) {
        return FileEditorManager.getInstance(project).getSelectedTextEditor();
    }
}
