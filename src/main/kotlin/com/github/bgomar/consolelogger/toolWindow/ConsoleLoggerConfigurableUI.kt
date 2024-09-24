package com.github.bgomar.consolelogger.toolWindow

import com.github.bgomar.consolelogger.toolWindow.setup.ConsoleLoggerSettings
import com.github.bgomar.consolelogger.toolWindow.setup.PropertiesConsoleLoggerToolSetup
import com.intellij.ui.components.JBTextField
import java.awt.*
import javax.swing.*

open class ConsoleLoggerConfigurableUI(
    propertiesConsoleLoggerTextField1: JTextField,
    propertiesConsoleLoggerTextField2: JTextField,
    propertiesConsoleLoggerTextField3: JTextField,
    propertiesConsoleLoggerTextField4: JTextField,
    propertiesConsoleLoggerTextField5: JTextField,
    propertiesConsoleLoggerTextField6: JTextField,
    propertiesConsoleLoggerTextField7: JTextField,
    propertiesConsoleLoggerTextField8: JTextField,
    propertiesConsoleLoggerTextField9: JTextField,
    propertiesConsoleLoggerSaveButton: JButton,
    propertiesConsoleLoggerLoad2Button: JButton,
    propertiesConsoleLoggerLoad1Button: JButton,
    propertiesConsoleLoggerCancelButton: JButton,
    propertiesConsoleLoggerDefaultButton1: JButton,
    propertiesConsoleLoggerDefaultButton2: JButton,
    propertiesConsoleLoggerDefaultButton3: JButton,
    propertiesConsoleLoggerDefaultButton4: JButton,
    propertiesConsoleLoggerDefaultButton5: JButton,
    propertiesConsoleLoggerDefaultButton6: JButton,
    propertiesConsoleLoggerDefaultButton7: JButton,
    propertiesConsoleLoggerDefaultButton8: JButton,
    propertiesConsoleLoggerDefaultButton9: JButton
) : PropertiesConsoleLoggerToolSetup(
    propertiesConsoleLoggerTextField1,
    propertiesConsoleLoggerTextField2,
    propertiesConsoleLoggerTextField3,
    propertiesConsoleLoggerTextField4,
    propertiesConsoleLoggerTextField5,
    propertiesConsoleLoggerTextField6,
    propertiesConsoleLoggerTextField7,
    propertiesConsoleLoggerTextField8,
    propertiesConsoleLoggerTextField9,
    propertiesConsoleLoggerSaveButton,
    propertiesConsoleLoggerLoad2Button,
    propertiesConsoleLoggerLoad1Button,
    propertiesConsoleLoggerCancelButton,
    propertiesConsoleLoggerDefaultButton1,
    propertiesConsoleLoggerDefaultButton2,
    propertiesConsoleLoggerDefaultButton3,
    propertiesConsoleLoggerDefaultButton4,
    propertiesConsoleLoggerDefaultButton5,
    propertiesConsoleLoggerDefaultButton6,
    propertiesConsoleLoggerDefaultButton7,
    propertiesConsoleLoggerDefaultButton8,
    propertiesConsoleLoggerDefaultButton9
) {
    private val ui: JPanel = JPanel()  // Or DialogPanel

    init {
        // Setup the layout and components for the UI here
        ui.layout = GridBagLayout()  // Example layout setup
        val settings = ConsoleLoggerSettings.getInstance()

        // Use settings to initialize text fields with current patterns
        val textFields = Array(9) { JBTextField(ConsoleLoggerSettings.getPattern(it)) }

        for (i in textFields.indices) {
            // For example, adding text fields to the UI
            ui.add(textFields[i]) // Add the text field to the panel
        }

        // Set up the Save button
        val saveButton = JButton("Save")
        saveButton.addActionListener {
            for (i in textFields.indices) {
                // Save the updated pattern back into the settings
                ConsoleLoggerSettings.setPattern(i, textFields[i].text)
            }
        }
        ui.add(saveButton)
    }

    override fun getComponent(): JComponent {
        return ui // Return the panel containing all UI elements
    }

    // Use ConsoleLoggerConfig to configure logger settings
    fun configureLoggerSettings(setting: ConsoleLoggerSettings) {
        val loggerConfig = ConsoleLoggerConfig(setting)
        val component = loggerConfig.getComponent()  // Get the UI from ConsoleLoggerConfig

        // Add the component to your main UI panel, if needed
        ui.add(component)
    }
}

private fun PropertiesConsoleLoggerToolSetup.getComponent(): JComponent {
    return getComponent()
}
