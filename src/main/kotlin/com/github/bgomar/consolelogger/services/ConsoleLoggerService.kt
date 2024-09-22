package com.github.bgomar.consolelogger.services

import com.github.bgomar.consolelogger.toolWindow.ConsoleLoggerConfigurableUI
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import javax.swing.JButton
import javax.swing.JTextField

@Service(Service.Level.PROJECT)
class ConsoleLoggerService(project: Project) {

    private val loggerUI: ConsoleLoggerConfigurableUI


    init {
        thisLogger().info("Initializing ConsoleLoggerService for project ${project.name}")
        thisLogger().warn("Don't forget to remove all non-needed sample code files from `plugin.xml`.")

        // Initialize loggerUI with JTextField and JButton arrays
        val textFields = Array(9) { JTextField() }
        val buttons = Array(9) { JButton("Default ${it + 1}") }

        loggerUI = ConsoleLoggerConfigurableUI(
            textFields[0], textFields[1], textFields[2], textFields[3], textFields[4],
            textFields[5], textFields[6], textFields[7], textFields[8],
            JButton("Save"), JButton("Load 2"), JButton("Load 1"),
            JButton("Cancel"), buttons[0], buttons[1], buttons[2], buttons[3], buttons[4],
            buttons[5], buttons[6], buttons[7], buttons[8]
        )
    }

    fun getRandomNumber() = (1..100).random()

    fun getUIComponent() = loggerUI // Use the instance of ConsoleLoggerConfigurableUI
}
