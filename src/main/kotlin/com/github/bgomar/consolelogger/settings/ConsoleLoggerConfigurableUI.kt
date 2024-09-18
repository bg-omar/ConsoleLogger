package com.github.bgomar.consolelogger.settings

import com.intellij.openapi.options.ConfigurableUi
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import javax.swing.JComponent

import com.github.bgomar.consolelogger.toolwindow.setup.PropertiesConsoleLoggerToolSetup

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*
import org.jetbrains.annotations.NotNull

class ConsoleLoggerConfigurableUI(
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
)

class ConsoleLoggerConfig(@NotNull setting: ConsoleLoggerSettings) : PropertiesConsoleLoggerToolSetup() {

  private val ui: DialogPanel = DialogPanel()

  init {
    ui.layout = GridBagLayout()
    val gbc = GridBagConstraints().apply {
      gridx = 0
      gridy = 0
    }

    val textFields = Array(9) { JBTextField() }
    val defaultButtons = Array(9) { JButton("Default") }

    for (i in textFields.indices) {
      // Adding label with shortcut info
      ui.add(JTextField("(CTRL + ALT + ${i + 1})"), gbc)
      gbc.gridx++

      // Creating text fields for patterns
      textFields[i].text = ConsoleLoggerSettings.getPattern(i)
      textFields[i].toolTipText = "Tooltip for pattern ${i + 1}"
      ui.add(textFields[i], gbc)
      gbc.gridx++

      // Adding default button
      defaultButtons[i].toolTipText = "Reset to default pattern"
      defaultButtons[i].addActionListener { textFields[i].text = ConsoleLoggerSettings.getPattern(i) }
      ui.add(defaultButtons[i], gbc)

      // Resetting grid column and moving to next row
      gbc.gridx = 0
      gbc.gridy++
    }
  }


  override fun reset(settings: ConsoleLoggerSettings) {
    ui.reset()
  }

  override fun isModified(settings: ConsoleLoggerSettings): Boolean {
    return ui.isModified()
  }

  override fun apply(settings: ConsoleLoggerSettings) {
    ui.apply()
  }

  override fun getComponent(): JComponent {
    return ui
  }
}