package com.github.bgomar.consolelogger

import com.intellij.find.FindModel
import com.intellij.find.FindUtil
import com.intellij.find.replaceInProject.ReplaceInProjectManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings

class ConsoleLoggerRemove : AnAction("Remove ConsoleLogger's Logs") {
  override fun actionPerformed(e: AnActionEvent) {
    val dlg = ConsoleLoggerRemoveDlg()
    if (!dlg.showAndGet()) return

    val project = e.getData(CommonDataKeys.PROJECT) ?: return
    val editor = e.getRequiredData(CommonDataKeys.EDITOR)

    val findLogModels = (0 until ConsoleLoggerSettings.getLogPatternsCount()).map { index ->
      createFindModelForLogPattern(ConsoleLoggerSettings.getPattern(index))
    }

    when (dlg.scope) {
      Scope.CURRENT_FILE -> findLogModels.forEach { findModel ->
        FindUtil.replace(project, editor, 0, findModel)
      }
      Scope.PROJECT -> {
        val replaceInProjectManager = ReplaceInProjectManager(project)
        findLogModels.forEach { findModel ->
          replaceInProjectManager.replaceInPath(findModel)
        }
      }
    }
  }

  private fun createFindModelForLogPattern(logPattern: String): FindModel {
    val removeLog = ".*" + logPattern.run {
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
        .replace("{FN}", ".*")
        .replace("{FP}", ".*")
        .replace("{LN}", "\\d*")
        .replace("{", "\\{")
        .replace("}", "\\}")
        .replace("$", "\\$")
    } + "\n"

    return FindModel().apply {
      stringToFind = removeLog
      stringToReplace = ""
      isPromptOnReplace = false
      isRegularExpressions = true
      isGlobal = true
      isPromptOnReplace = false
    }
  }
}
