package com.github.bgomar.consolelogger

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.toMutableProperty
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JSpinner
import javax.swing.SpinnerNumberModel


enum class Scope { CURRENT_FILE, PROJECT }

class ConsoleLoggerRemoveDlg : DialogWrapper(false) {
  var scope: Scope
  var numberInput: String = ""

  init {
    title = "Remove ConsoleLogger's Logs"
    scope = Scope.CURRENT_FILE
    init()
  }

  override fun createCenterPanel(): JComponent {
    val numberField = JSpinner(SpinnerNumberModel(1, 1, 9, 1))
    numberField.addChangeListener {
      numberInput = (numberField.value as Int).toString()
    }

    val pan = panel {
      buttonsGroup("Remove ConsoleLoggers in file? ") {
        row {
          radioButton("Yes, remove all ConsoleLoggers.", Scope.CURRENT_FILE)
        }
        row {
          radioButton("No, search Project for ConsoleLogger:", Scope.PROJECT)
          cell(numberField).onApply { scope = Scope.PROJECT }
        }
      }.bind(::scope.toMutableProperty(), Scope::class.java)
    }

    pan.minimumSize = Dimension(300, 100)
    return pan
  }
}