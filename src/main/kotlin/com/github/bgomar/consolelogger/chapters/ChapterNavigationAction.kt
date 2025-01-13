package com.github.bgomar.consolelogger.chapters


import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project

object ChapterNavigationAction {
    fun navigateToLine(project: Project, lineNumber: Int) {
        val editor: Editor? = FileEditorManager.getInstance(project).selectedTextEditor
        editor?.let {
            it.caretModel.moveToLogicalPosition(LogicalPosition(lineNumber - 1, 0))
            it.scrollingModel.scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER)
        }
    }
}