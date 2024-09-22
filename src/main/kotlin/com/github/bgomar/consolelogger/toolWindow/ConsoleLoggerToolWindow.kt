package com.github.bgomar.consolelogger.toolWindow

import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.components.JBTextField

import javax.swing.JButton
import javax.swing.JPanel

class ConsoleLoggerToolWindow(toolWindow: ToolWindow) {

    private val loggerPanel: JPanel

    init {
        // Create JTextFields and Buttons for the UI
        val textFields = Array(9) { JBTextField() }
        val buttons = Array(9) { JButton("Default ${it + 1}") }

        // Setup the UI using ConsoleLoggerConfigurableUI
        val ui = ConsoleLoggerConfigurableUI(
            textFields[0], textFields[1], textFields[2], textFields[3],
            textFields[4], textFields[5], textFields[6], textFields[7],
            textFields[8], JButton("Save"), JButton("Load 2"),
            JButton("Load 1"), JButton("Cancel"), buttons[0], buttons[1],
            buttons[2], buttons[3], buttons[4], buttons[5], buttons[6],
            buttons[7], buttons[8]
        )
        loggerPanel = ui.getComponent() as JPanel
    }

    fun getContent(): JPanel {
        return loggerPanel
    }
}
