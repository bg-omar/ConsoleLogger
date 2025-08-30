package com.github.bgomar.consolelogger

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project

object LogLineUpdater {
    private val logPattern = Regex("""console\.log\(".*Line: \d+.*"""")

    fun updateLogLines(document: Document, project: Project?) {
        if (project == null) return
        WriteCommandAction.runWriteCommandAction(project) {
            for (line in 0 until document.lineCount) {
                val lineStart = document.getLineStartOffset(line)
                val lineEnd = document.getLineEndOffset(line)
                val text = document.getText().substring(lineStart, lineEnd)
                if (logPattern.containsMatchIn(text)) {
                    val newLineText = updateLogLine(text, line + 1)
                    document.replaceString(lineStart, lineEnd, newLineText)
                }
            }
        }
    }

    private fun updateLogLine(logText: String, lineNumber: Int): String {
        return logText.replace(Regex("""Line: \d+"""), "Line: $lineNumber")
    }
}

