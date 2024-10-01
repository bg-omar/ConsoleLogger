package com.github.bgomar.consolelogger

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings
import com.intellij.find.FindModel
import com.intellij.find.FindUtil
import com.intellij.find.replaceInProject.ReplaceInProjectManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import kotlin.text.replace

class ConsoleLoggerRemove : AnAction("Remove ConsoleLogger's Logs") {

  override fun actionPerformed(e: AnActionEvent) {
    val project = e.getData(CommonDataKeys.PROJECT)!!
    val editor = e.getRequiredData(CommonDataKeys.EDITOR)

    // Proceed with log removal only if an editor is found
    val dlg = ConsoleLoggerRemoveDlg()
    if (!dlg.showAndGet()) return
    // Get the scope of the removal (current file or entire project)
    val scope = dlg.scope

    for (patternIndex in 1..ConsoleLoggerSettings.getPatternLength()) {
      // Generate the regex pattern for each console logger pattern
      val removePattern = generateRemovePattern(ConsoleLoggerSettings.getPattern(patternIndex))

      // Create the find model for each pattern
      val findModel = createFindModel(removePattern)

      // Perform replacement based on the scope (current file or project)
      when (scope) {
        Scope.CURRENT_FILE -> FindUtil.replace(project, editor, 0, findModel)
        Scope.PROJECT -> ReplaceInProjectManager(project).replaceInPath(findModel)
      }
    }
  }
  /**
   * Creates a [FindModel] configured for the find-and-replace operation.
   */
  private fun createFindModel(pattern: String): FindModel {
    return FindModel().apply {
      stringToFind = pattern
      stringToReplace = ""             // Empty replacement to remove logs
      isRegularExpressions = true      // Enable regex search
      isGlobal = true                  // Search across the entire project
      isPromptOnReplace = false        // Replace without prompting
    }
  }

  /**
   * Generates the regex pattern to remove the log statements
   * by escaping special characters and replacing placeholders.
   */
  private fun generateRemovePattern(pattern: String): String {
    return ".*" + pattern.run {
      escapeSpecialCharacters()
        .replace("\\$\\$", ".*")           // Match any variable text
        .replace("{FN}", ".*")             // Match any filename
        .replace("{FP}", ".*")             // Match any file path
        .replace("{LN}", "\\d+")           // Match line numbers
    } + "\\n"  // Ensure it matches the entire line
  }

  /**
   * Escapes special regex characters in the pattern string.
   */
  private fun String.escapeSpecialCharacters(): String {
    return this.replace("\\", "\\\\")
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
      .replace("{", "\\{")
      .replace("}", "\\}")
      .replace("$", "\\$")
  }



}
