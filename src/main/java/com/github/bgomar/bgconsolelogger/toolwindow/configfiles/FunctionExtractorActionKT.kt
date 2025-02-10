package com.github.bgomar.bgconsolelogger.toolwindow.configfiles

import com.github.bgomar.bgconsolelogger.toolwindow.setup.ConfigPresetToolSetup
import com.intellij.lang.javascript.psi.JSFunction
import com.intellij.lang.javascript.psi.ecmal4.JSClass
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil

class FunctionExtractorActionKT : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.project
        val editor: Editor? = FileEditorManager.getInstance(project!!).selectedTextEditor
        if (editor == null) {
            return
        }

        val psiFile: PsiFile? = PsiDocumentManager.getInstance(project).getPsiFile(editor.document)
        if (psiFile == null) {
            return
        }

        val selectedElement: PsiElement? = psiFile.findElementAt(editor.caretModel.offset)

        // Try to extract a TypeScript function or class
        val selectedFunction: JSFunction? = PsiTreeUtil.getParentOfType(selectedElement, JSFunction::class.java)
        val selectedClass: JSClass? = PsiTreeUtil.getParentOfType(selectedElement, JSClass::class.java)

        when {
            selectedFunction != null -> {
                val functionInfo = TypeScriptHandler().obfuscateFunction(selectedFunction)
                displayResult(functionInfo)
            }
            selectedClass != null -> {
                val classInfo = TypeScriptHandler().obfuscateClass(selectedClass)
                displayResult(classInfo)
            }
            else -> {
                Messages.showInfoMessage(project, "No function or class selected.", "Error")
            }
        }
    }

    private fun displayResult(info: String) {
        ConfigPresetToolSetup.getFunctionExtractorTextArea().text = info
    }
}

class TypeScriptHandler {
    private val variableMapping = mutableMapOf<String, String>()
    private var varCounter = 1
    private var funcCounter = 1
    private var propCounter = 1
    private var argCounter = 1
    private var typeCounter = 1

    // Helper function to generate new variable names
    private fun generateNewVariableName(originalName: String): String {
        return variableMapping.getOrPut(originalName) { "var${varCounter++}" }
    }

    // Helper function to generate new function names
    private fun generateNewFunctionName(): String {
        return "function${funcCounter++}"
    }

    // Helper function to generate new argument names
    private fun generateNewArgName(): String {
        return "arg${argCounter++}"
    }

    // Helper function to generate new types for non-standard types
    private fun generateNewTypeName(originalName: String): String {
        return "type${typeCounter++}"
    }

    // Helper function to generate new property names for "this."
    private fun generateNewPropertyName(): String {
        return "prop${propCounter++}"
    }

    // Obfuscate the function, removing the unnecessary header
    fun obfuscateFunction(function: JSFunction): String {
        val result = StringBuilder()

        // Obfuscate the function name
        val obfuscatedFunctionName = generateNewFunctionName()

        // Obfuscate parameters and types
        val parameterList = mutableListOf<String>()
        function.parameterList?.parameters?.forEach { param ->
            val paramName = generateNewArgName()
            val paramType = param.typeElement?.text
            val obfuscatedType = if (paramType != "string" && paramType != "number" && paramType != "boolean") {
                generateNewTypeName(paramType ?: "")
            } else {
                paramType
            }
            parameterList.add("$paramName: $obfuscatedType")
        }

        // Function Signature
        result.append(obfuscatedFunctionName).append("(").append(parameterList.joinToString(", ")).append("): void {\n")

        // Body: Replace variable names inside the function body
        val body: PsiElement? = function.block ?: function.block
        body?.let {
            var obfuscatedBody = it.text

            // Replace all occurrences of parameter names with obfuscated names
            function.parameterList?.parameters?.forEach { param ->
                obfuscatedBody = obfuscatedBody.replace(param.text, generateNewArgName())
            }

            // Recognize and obfuscate TypeScript constructs (classes, variables, etc.)
            obfuscatedBody = recognizeAndObfuscateTypescriptConstructs(obfuscatedBody)

            // Add proper indentation for readability
            result.append(indentText(obfuscatedBody, 2)).append("\n")
        }

        result.append("}")
        return result.toString()
    }

    // Obfuscate a TypeScript class (if needed in the future)
    fun obfuscateClass(jsClass: JSClass): String {
        val result = StringBuilder()
        result.append("class ${generateNewFunctionName()} {\n")

        // Obfuscate class methods
        jsClass.functions.forEach { method ->
            result.append("  ").append(generateNewFunctionName()).append("() {}\n")
        }

        result.append("}")
        return result.toString()
    }

    // Recognize and obfuscate various TypeScript constructs
    private fun recognizeAndObfuscateTypescriptConstructs(bodyText: String): String {
        // Obfuscate variables and constants
        var updatedBody = bodyText.replace(Regex("""(let|const|var)\s+(\w+)""")) { matchResult ->
            "${matchResult.groupValues[1]} ${generateNewVariableName(matchResult.groupValues[2])}"
        }

        // Obfuscate "this." property references
        updatedBody = updatedBody.replace(Regex("""this\.(\w+)""")) { matchResult ->
            "this.${generateNewPropertyName()}"
        }

        return updatedBody
    }

    // Helper function to indent the text for readability
    private fun indentText(text: String, indentLevel: Int): String {
        val indent = " ".repeat(indentLevel * 2)
        return text.split("\n").joinToString("\n") { line -> indent + line.trim() }
    }
}