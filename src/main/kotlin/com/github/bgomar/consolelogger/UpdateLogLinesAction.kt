package com.github.bgomar.consolelogger

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project

class UpdateLogLinesAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.project
        if (project == null) {
            println("No project found.")
            return
        }

        // Get the current editor and document for the active file in the project
        val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: run {
            println("No active editor found.")
            return
        }

        val document: Document = editor.document

        // Regex pattern to find log statements with line numbers
        val logPattern = Regex("""console\.log\(".*Line: \d+.*"""")

        WriteCommandAction.runWriteCommandAction(project) {
             // Iterate through the document, updating line numbers
            for (line in 0 until document.lineCount) {
                val lineStart = document.getLineStartOffset(line)
                val lineEnd = document.getLineEndOffset(line)
                val text = document.getText().substring(lineStart, lineEnd)

                // Check if the line contains a logger with a line number
                if (logPattern.containsMatchIn(text)) {
                    val newLineText = updateLogLine(text, line + 1)
                    document.replaceString(lineStart, lineEnd, newLineText)
                }
            }
        }
    }

    // Helper function to update the log line text with the correct line number
    private fun updateLogLine(logText: String, lineNumber: Int): String {
        // Replace the old line number with the new one
        return logText.replace(Regex("""Line: \d+"""), "Line: $lineNumber")
    }
}
