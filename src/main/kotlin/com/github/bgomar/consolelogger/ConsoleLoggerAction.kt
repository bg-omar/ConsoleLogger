package com.github.bgomar.consolelogger

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings
import com.intellij.lang.javascript.JavascriptLanguage
import com.intellij.lang.javascript.psi.JSBlockStatement
import com.intellij.lang.javascript.psi.JSElement
import com.intellij.lang.javascript.psi.JSFunctionExpression
import com.intellij.lang.javascript.psi.JSIfStatement
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.CaretState
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil


class ConsoleLoggerAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val presentation = e.presentation.text
        val patternText: Int = presentation.toIntOrNull() ?: 0
        val patternIndex: Int = patternText
        val editor = e.getData(CommonDataKeys.EDITOR)
        if (editor == null) {
            println("Editor is missing. Cannot perform the action.")
            return
        }
        val vFile: VirtualFile? = e.getData(PlatformDataKeys.VIRTUAL_FILE)
        var variableName = moveCursorToInsertionPoint(editor)?.trim()
        if (variableName.isNullOrEmpty()) variableName = ""
        val pattern = ConsoleLoggerSettings.getPattern(patternIndex).run {
            replace("{FN}", vFile?.name ?: "filename")
                .replace("{FP}", vFile?.path ?: "file_path")
                .replace("{LN}", "Line: "+(editor.caretModel.currentCaret.logicalPosition.line + 2).toString())
        }
        val insertionPositions = "\\$\\$".toRegex().findAll(pattern).map { it.range.first }.toList()
        val lineToInsert = if (variableName == "\n") {
            "\n${pattern.replace("$$", "")}"
        } else pattern.replace("$$", variableName)
        val line2insert = lineToInsert.replace("<CR>", "")

        val caretOffset = editor.caretModel.currentCaret.offset
        val lineNumber = editor.document.getLineNumber(caretOffset)
        val lineStartOffset = editor.document.getLineStartOffset(lineNumber)
        val lineEndOffset = editor.document.getLineEndOffset(lineNumber)
        val currentLineText = editor.document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))
        // Remove indentation for logger insertion
        // val indentation = currentLineText.takeWhile { it == ' ' || it == '\t' }

        val insertAfter = caretOffset >= lineEndOffset - 1 || currentLineText.trim().endsWith(";") || currentLineText.trim().endsWith("}")

        val runnable = {
            if (variableName != "") {
                // Get indentation from the statement line only
                val statementLine = editor.document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))
                val statementIndentation = statementLine.takeWhile { it == ' ' || it == '\t' }
                if (insertAfter) {
                    // Insert logger on a new line after the statement, with the same indentation as the statement
                    editor.document.insertString(lineEndOffset, "\n" + statementIndentation + line2insert.trimStart())
                } else {
                    // Insert before the statement, at the start of the line, with statement's indentation
                    editor.document.insertString(lineStartOffset, statementIndentation + line2insert.trimStart() + "\n")
                }
            } else {
                val offset = editor.caretModel.currentCaret.offset
                editor.document.insertString(offset, line2insert)
            }
        }
        WriteCommandAction.runWriteCommandAction(editor.project, runnable)
        positionCaret(editor, insertionPositions, line2insert, variableName.replace("<CR>", "").trim())
        // Automatically update log line numbers after insertion
        LogLineUpdater.updateLogLines(editor.document, editor.project)
    }

  private fun positionCaret(editor: Editor, insertionPositions: List<Int>, lineToInsert: String, variableName: String) {
    val offset = editor.caretModel.currentCaret.offset
    val logicalPosition = editor.offsetToLogicalPosition(offset)

    if (insertionPositions.size > 1) {
      editor.caretModel.caretsAndSelections = listOf(
        CaretState(
          LogicalPosition(
            logicalPosition.line,
            logicalPosition.column + insertionPositions[0]
          ),
          LogicalPosition(
            logicalPosition.line,
            logicalPosition.column + insertionPositions[0]
          ),
          LogicalPosition(
            logicalPosition.line,
            logicalPosition.column + insertionPositions[0] + variableName.length
          )
        ),
        CaretState(
          LogicalPosition(
            logicalPosition.line,
            logicalPosition.column + insertionPositions[1] + variableName.length - 2
          ),
          LogicalPosition(
            logicalPosition.line,
            logicalPosition.column + insertionPositions[1] + variableName.length - 2
          ),
          LogicalPosition(
            logicalPosition.line,
            logicalPosition.column + insertionPositions[1] + variableName.length * 2 - 2
          )
        )
      )
    } else if (insertionPositions.isNotEmpty()) {
      // Safely handle cases where there's only one insertion position
      editor.caretModel.caretsAndSelections =
        listOf(
          CaretState(
            LogicalPosition(
              logicalPosition.line,
              logicalPosition.column + insertionPositions[0]),
            LogicalPosition(
              logicalPosition.line,
              logicalPosition.column + insertionPositions[0]),
            LogicalPosition(
              logicalPosition.line,
              logicalPosition.column + insertionPositions[0] + variableName.length)
          )
        )
    }
  }
  /**
   * search for the cursor insertion point
   * return the name of the element to log
   */
  private fun moveCursorToInsertionPoint(editor: Editor): String? {
      val psiFile = PsiFileFactory.getInstance(editor.project)
          .createFileFromText("dummy.ts", JavascriptLanguage.INSTANCE, editor.document.text)

      val valueToLog: String
      val element: PsiElement?
      val offset: Int

      if (editor.selectionModel.hasSelection()) {
          val value = editor.selectionModel.selectedText
          offset = editor.selectionModel.selectionStart
          element = psiFile.findElementAt(offset)
          valueToLog = value ?: "<CR>"
      } else {
          offset = editor.caretModel.currentCaret.offset
          val elementAtCursor = psiFile.findElementAt(offset)
          if (elementAtCursor?.text?.replace(" ", "")?.endsWith("\n\n") == true) return ""
          element = findElementToLogForSelection(elementAtCursor!!)
          valueToLog = element?.text?.replace(" ", "") ?: "<CR>"
      }

      // --- NEW LOGIC FOR VARIABLE DECLARATION ---
      if (element != null && (element.node.elementType.toString() == "JS:IDENTIFIER" || element.node.elementType.toString() == "JS:REFERENCE_EXPRESSION")) {
          // Check if parent is a variable declaration
          val parent = element.parent
          if (parent != null && (parent.node.elementType.toString() == "JS:DEFINITION_EXPRESSION" || parent.node.elementType.toString() == "JS:VAR_STATEMENT")) {
              // Move caret to END of declaration statement
              editor.caretModel.moveToOffset(parent.textRange.endOffset)
              return valueToLog
          }
      }

      // If the element is a variable declaration or assignment, insert after the statement
      if (element != null && (element.node.elementType.toString() == "JS:VAR_STATEMENT" || element.node.elementType.toString() == "JS:DEFINITION_EXPRESSION" || element.node.elementType.toString() == "JS:EXPRESSION_STATEMENT")) {
          editor.caretModel.moveToOffset(element.textRange.endOffset)
          return valueToLog
      }

      // If the element is inside an object literal, insert after the assignment statement
      if (element != null && element.hasParentOfType("JS:OBJECT_LITERAL", 2)) {
          var parent = element.parent
          var levels = 0
          while (parent != null && levels < 10) {
              val type = parent.node.elementType.toString()
              if (type == "JS:EXPRESSION_STATEMENT" || type == "JS:VAR_STATEMENT") {
                  editor.caretModel.moveToOffset(parent.textRange.endOffset)
                  break
              }
              parent = parent.parent
              levels++
          }
          return valueToLog
      }

      // Insert after loggable statement (variable, assignment, or expression)
      if (element != null && isLoggableStatement(element)) {
          val statementEnd = getStatementEndOffset(element)
          if (statementEnd != null) {
              editor.caretModel.moveToOffset(statementEnd)
              return valueToLog
          }
      }

      // Fallback to block logic for blocks (functions, if, etc.)
      val block = findBlockForElement(element ?: psiFile.findElementAt(offset) ?: return null)
      when {
          block is JSFunctionExpression -> {
              val body = PsiTreeUtil.findChildOfType(block, JSBlockStatement::class.java)
              if (body != null) {
                  editor.caretModel.moveToOffset(body.textOffset + 1)
              } else {
                  val expression = PsiTreeUtil.findChildOfType(block, JSElement::class.java)
                  if (expression != null) {
                      editor.caretModel.moveToOffset(expression.textRange.endOffset)
                  }
              }
          }
          block is JSIfStatement -> {
              editor.caretModel.moveToOffset(block.prevSibling.textRange.startOffset - 1)
          }
          block != null -> {
              val returnStatement = findReturnStatementInBlock(block)
              if (returnStatement != null) {
                  editor.caretModel.moveToOffset(returnStatement.textRange.startOffset)
              } else {
                  editor.caretModel.moveToOffset(block.textRange.endOffset)
              }
          }
      }
      return valueToLog
  }

  // Helper: Is this element a loggable statement (variable, assignment, or expression)?
  private fun isLoggableStatement(element: PsiElement): Boolean {
      val type = element.node.elementType.toString()
      return type == "JS:IDENTIFIER" || type == "JS:REFERENCE_EXPRESSION" || type == "JS:BINARY_EXPRESSION" || type == "JS:DEFINITION_EXPRESSION" || type == "JS:VAR_STATEMENT" || type == "JS:EXPRESSION_STATEMENT"
  }

  // Helper: Get the end offset of the statement for the element
  private fun getStatementEndOffset(element: PsiElement): Int? {
      var stmt = element
      // Climb up to the enclosing statement if needed
      while (stmt.parent != null && stmt.parent.node.elementType.toString().endsWith("STATEMENT")) {
          stmt = stmt.parent
      }
      return stmt.textRange?.endOffset
  }

  /**
   * Finds a child of the specified type in the PSI tree.
   */
  fun <T : PsiElement> PsiElement.childOfType(klass: Class<T>): T? {
    return this.children.filterIsInstance(klass).firstOrNull()
  }

  /**
   * Find the return statement in a given block
   */
  private fun findReturnStatementInBlock(block: PsiElement): PsiElement? {
    for (child in block.children) {
      if (child.node.elementType.toString() == "JS:RETURN_STATEMENT") {
        return child  // Return statement found
      }
    }
    return null  // No return statement found
  }


  /**
   * when the cursor is on a loggable identifier
   */
  private fun findElementToLogForSelection(
    element: PsiElement
  ): PsiElement? {

    val elementType = element.node.elementType.toString()
    val parentElementType = element.parent.node.elementType.toString()
    when {
      elementType == "WHITE_SPACE" && element.text.replace(" ", "").startsWith("\n\n") -> return null
      element.prevSibling != null
        && element.prevSibling.node.elementType.toString() == "JS:DOT"
      -> return findElementToLogForSelection(element.parent)

      (elementType != "JS:IDENTIFIER"
        && elementType != "JS:REFERENCE_EXPRESSION"
        && elementType != "JS:BINARY_EXPRESSION")
        || (parentElementType == "JS:REFERENCE_EXPRESSION" && elementType != "JS:IDENTIFIER")
        || parentElementType == "JS:PROPERTY"
      -> {
        val block = findBlockForElement(element)
        return when {
          element.text.trim(' ') == "\n" && (element.prevSibling?.lastChild?.text == ";") -> null
          block?.text?.trim() == "{" -> null
          block?.node?.elementType.toString() == "JS:IF_STATEMENT" -> element.prevSibling?.let {
            findElementToLogForSelection(
              element.prevSibling
            )
          } ?: element
          else -> findElementToLogForBlock(block)
        }
      }

      elementType == "JS:IDENTIFIER" && parentElementType == "JS:VARIABLE" -> return findElementToLogForBlock(
        element
      )
      elementType == "JS:REFERENCE_EXPRESSION"
        && parentElementType != "JS:BINARY_EXPRESSION" -> {
        return findElementToLogForSelection(element.parent)
      }

      (elementType == "JS:IDENTIFIER"
        && !element.hasParentOfType("JS:ARGUMENT_LIST", 2)
        && element.hasParentOfType("JS:CALL_EXPRESSION", 2))
        && element.prevSibling == null -> return null
    }

    return element
  }

  /**
   * find the element to log inside a given block
   */
  private fun findElementToLogForBlock(element: PsiElement?): PsiElement? {
    element ?: return null
    val elementType = element.node.elementType.toString()
    val parentType = element.parent.node.elementType.toString()

    when {
      (elementType == "JS:IDENTIFIER" && parentType != "JS:PROPERTY")
        || elementType == "JS:DEFINITION_EXPRESSION"
        || (elementType == "JS:REFERENCE_EXPRESSION" && parentType == "JS:REFERENCE_EXPRESSION")
      -> return element
      elementType == "JS:VARIABLE" -> return element.firstChild
      elementType == "JS:CALL_EXPRESSION" -> return null
    }

    if (element.firstChild == null) {
      return findElementToLogForBlock(element.nextSibling)
    }

    return findElementToLogForBlock(element.firstChild)
  }

  /**
   * find the block containing this element
   */
  private fun findBlockForElement(element: PsiElement): PsiElement? {

    val elementType = element.node.elementType.toString()
    val parentElementType = if (element.parent == null) {
      return null
    } else element.parent.node.elementType.toString()

    when {
      (elementType == "JS:EXPRESSION_STATEMENT" && parentElementType != "FILE") -> return element
      elementType == "JS:VAR_STATEMENT" -> return element
      elementType == "JS:IF_STATEMENT" -> return element

      element.text.trim(' ') == "{" -> return element
      element.text.trim(' ') == "\n" -> return findBlockForElement(element.prevSibling)
    }

    return findBlockForElement(element.parent)
  }

  private fun PsiElement.hasParentOfType(type: String, maxRecursion: Int, recursionLevel: Int = 0): Boolean {
    return if (this.parent.node.elementType.toString() == type) {
      true
    } else {
      return if (this.parent.node.elementType.toString() != "FILE" && recursionLevel < maxRecursion)
        this.parent.hasParentOfType(type, maxRecursion, recursionLevel + 1)
      else false
    }
  }
}