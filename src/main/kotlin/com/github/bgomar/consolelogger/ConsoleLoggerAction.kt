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

    val patternText: Int = presentation.toIntOrNull() ?: 0  // Default value if conversion fails
    val patternIndex: Int = patternText

    // Check if the editor is available
    val editor = e.getData(CommonDataKeys.EDITOR)
    if (editor == null) {
        println("Editor is missing. Cannot perform the action.")
        return
    }
    val actionManager = EditorActionManager.getInstance()
    val startNewLineHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_START_NEW_LINE)

    val vFile: VirtualFile? = e.getData(PlatformDataKeys.VIRTUAL_FILE)

    val variableName = moveCursorToInsertionPoint(editor)?.trim()
    if (variableName.isNullOrEmpty()) return


    val pattern = ConsoleLoggerSettings.getPattern(patternIndex).run {
      replace("{FN}", vFile?.name ?: "filename")
        .replace("{FP}", vFile?.path ?: "file_path")
        .replace("{LN}", "Line: "+(editor.caretModel.currentCaret.logicalPosition.line + 2).toString())
    }

    val insertionPositions = "\\$\\$".toRegex().findAll(pattern).map { it.range.first }.toList()

    val lineToInsert = if (variableName == "\n") {
      "\n${pattern.replace("$$", "")}"
    } else
      pattern.replace("$$", variableName)

      variableName.let {
        val line2insert = lineToInsert.replace("<CR>", "")

        val runnable = {
          if (variableName != "") {
            startNewLineHandler.execute(editor, editor.caretModel.primaryCaret, e.dataContext)
          }

          val offset = editor.caretModel.currentCaret.offset
          editor.document.insertString(offset, line2insert)
        }
        WriteCommandAction.runWriteCommandAction(editor.project, runnable)
        positionCaret(editor, insertionPositions, line2insert, variableName.replace("<CR>", "").trim())
      }
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
    // Parse the file as a TypeScript file
    val psiFile = PsiFileFactory.getInstance(editor.project)
      .createFileFromText("dummy.ts", JavascriptLanguage.INSTANCE, editor.document.text)

    val valueToLog: String
    val element: PsiElement?
    val offset: Int

    // If the user has selected text (e.g., a variable)
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

    if (valueToLog.startsWith("\n") && element?.hasParentOfType("JS:OBJECT_LITERAL", 2) != true) {
      return "\n"
    }

    val block = findBlockForElement(element ?: psiFile.findElementAt(offset) ?: return null)

    when {
      // If it's an arrow function, insert inside the function body
      block is JSFunctionExpression -> {
        val body = PsiTreeUtil.findChildOfType(block, JSBlockStatement::class.java)
        if (body != null) {
          // Move the cursor inside the block, after the opening '{'
          editor.caretModel.moveToOffset(body.textOffset + 1)
        } else {
          // Handle the case where the arrow function has an expression body
          val expression = PsiTreeUtil.findChildOfType(block, JSElement::class.java)
          if (expression != null) {
            // Move the cursor to the end of the expression
            editor.caretModel.moveToOffset(expression.textRange.endOffset)
          }
        }
      }
      block is JSIfStatement -> {
        // For "if" statements, insert line above
        editor.caretModel.moveToOffset(block.prevSibling.textRange.startOffset - 1)
      }
      block != null -> {
        // Check if there is a return statement in the block
        val returnStatement = findReturnStatementInBlock(block)
        if (returnStatement != null) {
          // Move the caret just before the return statement
          editor.caretModel.moveToOffset(returnStatement.textRange.startOffset)
        } else {
          // Otherwise, move to the end of the block
          editor.caretModel.moveToOffset(block.textRange.endOffset)
        }
      }
    }

    return valueToLog
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
