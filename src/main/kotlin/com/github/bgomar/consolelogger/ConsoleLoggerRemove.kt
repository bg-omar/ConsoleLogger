package com.github.bgomar.consolelogger

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings
import com.intellij.find.FindModel
import com.intellij.find.replaceInProject.ReplaceInProjectManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import kotlin.text.replace

class ConsoleLoggerRemove : AnAction("Remove ConsoleLogger's Logs") {

  override fun actionPerformed(e: AnActionEvent) {
    val dlg = ConsoleLoggerRemoveDlg()
    if (!dlg.showAndGet()) return

    val project = e.getData(CommonDataKeys.PROJECT) ?: return
    val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: return
    val document = editor.document

    // Collect dynamic log patterns from ConsoleLoggerSettings
    val logPatterns = (0 until ConsoleLoggerSettings.getLogPatternsCount()).map { index ->
      createRegexFromLogPattern(ConsoleLoggerSettings.getPattern(index))
    }

    WriteCommandAction.runWriteCommandAction(project) {
      when (dlg.scope) {
        Scope.CURRENT_FILE -> {
          logPatterns.forEach { logPattern ->
            removeLogsInDocument(document, logPattern)
          }
        }
        Scope.PROJECT -> {
          logPatterns.forEach { logPattern ->
            ApplicationManager.getApplication().invokeLater {
              removeLogsInProject(project, logPattern)
            }
          }
        }
      }
    }
  }

  /**
   * Converts log patterns into regex patterns.
   */
  private fun createRegexFromLogPattern(logPattern: String): Regex {
    val removeLog = logPattern.run {
      replace("\\", "\\\\")
        .replace("(", "\\(")
        .replace(")", "\\)")
        .replace("[", "\\[")
        .replace("]", "\\]")
        .replace("^", "\\^")
        .replace("+", "\\+")
        .replace("?", "\\?")
        .replace("|", "\\|")
        .replace(".", "\\.")
        .replace("*", "\\*")
        .replace("$$", ".*")
        .replace("{FN}", ".*")  // Match any filename
        .replace("{FP}", ".*")  // Match any file path
        .replace("{LN}", "Line: \\d+") // Match any line number
        .replace("{", "\\{")
        .replace("}", "\\}")
        .replace("$", "\\$")
    }
    return Regex(removeLog)
  }

  /**
   * Removes log statements from the current document.
   */
  private fun removeLogsInDocument(document: Document, logPattern: Regex) {
    // Iterate through the document backwards to avoid indexing issues when removing lines
    for (line in document.lineCount - 1 downTo 0) {
      val lineStart = document.getLineStartOffset(line)
      val lineEnd = document.getLineEndOffset(line)
      val text = document.text.substring(lineStart, lineEnd).trim()

      // If the line contains a logger with a line number, remove it
      if (logPattern.containsMatchIn(text)) {
        // Remove the entire line including the newline character (if any)
        val nextLineStart = if (line + 1 < document.lineCount) {
          document.getLineStartOffset(line + 1)
        } else {
          document.textLength
        }

        document.deleteString(lineStart, nextLineStart)
      }
    }
  }


  /**
   * Removes log statements matching the pattern throughout the project.
   * It defers the `replaceInPath` operation using ApplicationManager.invokeLater.
   */
  private fun removeLogsInProject(project: Project, logPattern: Regex) {
    val replaceInProjectManager = ReplaceInProjectManager(project)

    val findModel = FindModel().apply {
      stringToFind = logPattern.pattern.toString()  // Use the regex pattern
      stringToReplace = ""  // Replace matched logs with an empty string (removal)
      isRegularExpressions = true
      isGlobal = true
    }

    // Invoke replaceInPath operation after the write action is done
    ApplicationManager.getApplication().invokeLater {
      replaceInProjectManager.replaceInPath(findModel)
    }
  }
  }
