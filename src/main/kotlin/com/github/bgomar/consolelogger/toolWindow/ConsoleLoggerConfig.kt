package com.github.bgomar.consolelogger.toolWindow

import com.github.bgomar.consolelogger.toolWindow.setup.ConsoleLoggerSettings
import com.github.bgomar.consolelogger.toolWindow.setup.PropertiesConsoleLoggerToolSetup
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import org.jetbrains.annotations.NotNull
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JTextField


class ConsoleLoggerConfig(@NotNull setting: ConsoleLoggerSettings) : PropertiesConsoleLoggerToolSetup(   // Initialize all JTextFields and JButtons
    JBTextField(ConsoleLoggerSettings.getPattern(0)),
    JBTextField(ConsoleLoggerSettings.getPattern(1)),
    JBTextField(ConsoleLoggerSettings.getPattern(2)),
    JBTextField(ConsoleLoggerSettings.getPattern(3)),
    JBTextField(ConsoleLoggerSettings.getPattern(4)),
    JBTextField(ConsoleLoggerSettings.getPattern(5)),
    JBTextField(ConsoleLoggerSettings.getPattern(6)),
    JBTextField(ConsoleLoggerSettings.getPattern(7)),
    JBTextField(ConsoleLoggerSettings.getPattern(8)),
    JButton("Save"),
    JButton("Load 2"),
    JButton("Load 1"),
    JButton("Cancel"),
    JButton("Default 1"),
    JButton("Default 2"),
    JButton("Default 3"),
    JButton("Default 4"),
    JButton("Default 5"),
    JButton("Default 6"),
    JButton("Default 7"),
    JButton("Default 8"),
    JButton("Default 9")) {

    private val ui: DialogPanel = DialogPanel().apply {
        layout = GridBagLayout()
    }

    init {
        val gbc = GridBagConstraints().apply {
            gridx = 0
            gridy = 0
        }

        val textFields = Array(9) { JBTextField() }
        val defaultButton = Array(9) { JButton("Default") }

        for (i in 0..8) {
            ui.add(JTextField("(CTRL + ALT + ${i + 1}) "), gbc)
            gbc.gridx++

            textFields[i].apply {
                text = ConsoleLoggerSettings.getPattern(i)
                toolTipText = "Tooltip for pattern ${i + 1}"
            }
            ui.add(textFields[i], gbc)
            gbc.gridx++

            defaultButton[i].apply {
                addActionListener { textFields[i].text = ConsoleLoggerSettings.getPattern(i) }
                toolTipText = "Reset to default pattern"
            }
            ui.add(defaultButton[i], gbc)
            gbc.gridx = 0
            gbc.gridy++
        }
    }

    override fun getComponent(): JComponent {
        return ui
    }
}
