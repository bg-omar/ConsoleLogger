package com.github.bgomar.consolelogger

import com.intellij.lang.javascript.JavascriptLanguage
import com.intellij.lang.javascript.psi.*



import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.CaretState
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.github.bgomar.consolelogger.tools.ConsoleLoggerSettings
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


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

    val variableName = moveCursorToInsertionPoint(editor)
    val logVar = variableName?.trim()

    val pattern = ConsoleLoggerSettings.getPattern(patternIndex).run {
      replace("{FN}", vFile?.name ?: "filename").replace("{FP}", vFile?.path ?: "file_path")
        .replace("{LN}", (editor.caretModel.currentCaret.logicalPosition.line + 2).toString())
    }

    val insertionPositions = "\\$\\$".toRegex().findAll(pattern)
      .map { it.range.first }
      .toList()

    val lineToInsert = if (logVar == "\n") {
      "\n${pattern.replace("$$", "")}"
    } else
      pattern.replace("$$", "$logVar")

    variableName?.let {
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
    print("positionCaret.lineToInsert: "); println(lineToInsert)
    val offset = editor.caretModel.currentCaret.offset
    val logicalPosition = editor.offsetToLogicalPosition(offset)
    print("positionCaret .offset: "); println(offset);
    print(" positionCaret.logicalPosition: "); println(logicalPosition);

    editor.caretModel.caretsAndSelections =
      listOf(
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
    println(editor.caretModel.caretsAndSelections)
  }

  /**
   * search for the cursor insertion point
   * return the name of the element to log
   */
  private fun moveCursorToInsertionPoint(editor: Editor): String? {
    // parse the file as a simple JavaScript file
    val psiFile = PsiFileFactory.getInstance(editor.project).createFileFromText("dummy.js", JavascriptLanguage.INSTANCE, editor.document.text)

    val valueToLog: String
    print(" moveCursorToInsertionPoint.psiFile: "); println(psiFile);
    val element: PsiElement?
    val offset: Int

    if (editor.selectionModel.hasSelection()) {
      val value = editor.selectionModel.selectedText

      offset = editor.selectionModel.selectionStart
      element = psiFile.findElementAt(offset)

      valueToLog = value ?: "<CR>"
      print(" moveCursorToInsertionPoint.element: "); println(element);
      print(" moveCursorToInsertionPoint.valueToLog: "); println(valueToLog);
    } else {
      offset = editor.caretModel.currentCaret.offset

      val elementAtCursor = psiFile.findElementAt(offset)

      if (elementAtCursor?.text?.replace(" ", "")?.endsWith("\n\n") == true) return ""

      element = findElementToLogForSelection(elementAtCursor!!)

      valueToLog = element?.text?.replace(" ", "") ?: "<CR>"
      print(" moveCursorToInsertionPoint.elementAtCursor: "); println(elementAtCursor);
      print(" moveCursorToInsertionPoint.element: "); println(element);
      print(" moveCursorToInsertionPoint.valueToLog: "); println(valueToLog);
    }

    if (valueToLog.startsWith("\n") && element?.hasParentOfType("JS:OBJECT_LITERAL", 2) != true) {
      return "\n"
    }

    val block = findBlockForElement(element ?: psiFile.findElementAt(offset) ?: return null)
    print("157 ---------------------------------------------- block: "); println(block);

    when {
      block is JSIfStatement -> {
        // for "if" statements insert line above
        editor.caretModel.moveToOffset(block.prevSibling.textRange.endOffset - 1)
      }
      block is JSFunction -> {
        // for "if" statements insert line above
        editor.caretModel.moveToOffset(block.prevSibling.textRange.endOffset + 1)
      }
      block != null -> editor.caretModel.moveToOffset(block.textRange.endOffset)
    }

    return valueToLog
  }

  /**
   * when the cursor is on a loggable identifier
   */
  private fun findElementToLogForSelection(element: PsiElement): PsiElement? {

    val elementType = element.node.elementType.toString()
    val parentElementType = element.parent.node.elementType.toString()
    print("findElementToLogForSelection.element: "); println(element);
    print("findElementToLogForSelection.elementType: ");  println(elementType);
    print("findElementToLogForSelection.parentElementType: ");   println(parentElementType);
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

    print("219 -------> element: "); println(element);
    return element
  }

  /**
   * find the element to log inside a given block
   */
  private fun findElementToLogForBlock(element: PsiElement?): PsiElement? {
    element ?: return null
    val elementType = element.node.elementType.toString()
    val parentType = element.parent.node.elementType.toString()
    print("230 findElementToLogForBlock.elementType: "); println(elementType);
    print("231 findElementToLogForBlock.parentType: "); println(parentType);
    when {
      (elementType == "JS:IDENTIFIER" && parentType != "JS:PROPERTY")
        || elementType == "JS:DEFINITION_EXPRESSION"
        || (elementType == "JS:REFERENCE_EXPRESSION" && parentType == "JS:REFERENCE_EXPRESSION")
      -> return element
      elementType == "JS:VARIABLE" -> return element.firstChild
      elementType == "JS:CALL_EXPRESSION" -> return null
    }

    if (element.firstChild == null) {
      print("238 -------> findElementToLogForBlock(element.nextSibling): "); println(findElementToLogForBlock(element.nextSibling));
      return findElementToLogForBlock(element.nextSibling)
    }
    print("245 -------> findElementToLogForBlock(element.firstChild): "); println(findElementToLogForBlock(element.firstChild));

    return findElementToLogForBlock(element.firstChild)
   }

  /**
   * find the block containing this element
   */
  private fun findBlockForElement(element: PsiElement): PsiElement? {

    val elementType = element.node.elementType.toString()
    print("262 .....elementType: "); println(elementType);

    val parentElementType = if (element.parent == null) {
      return null
    } else element.parent.node.elementType.toString()
    print("265 -------> parentElementType: "); println(parentElementType);
    when {
      (elementType == "JS:EXPRESSION_STATEMENT" && parentElementType != "FILE") -> return element
      elementType == "JS:VAR_STATEMENT" -> return element
      elementType == "JS:IF_STATEMENT" -> return element
      elementType ==  "JS:FUNCTION_DECLARATION" -> return element

      element.text.trim(' ') == "{" -> return element
      element.text.trim(' ') == "\n" -> return findBlockForElement(element.prevSibling)
       }

    print("277 (element.parent): "); println(element.parent);

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