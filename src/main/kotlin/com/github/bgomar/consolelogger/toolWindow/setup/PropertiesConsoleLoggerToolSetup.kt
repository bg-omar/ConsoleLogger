package com.github.bgomar.consolelogger.toolWindow.setup

import com.intellij.openapi.options.ConfigurableUi
import com.intellij.openapi.ui.DialogPanel
import org.jetbrains.annotations.NotNull
import javax.swing.*

open class PropertiesConsoleLoggerToolSetup() : AbstractToolSetup(), ConfigurableUi<ConsoleLoggerSettings> {

    private var propertiesConsoleLoggerPreset = 1

    companion object {
        var propertiesConsoleLoggerTextField1 = JTextField()
        var propertiesConsoleLoggerTextField2 = JTextField()
        var propertiesConsoleLoggerTextField3 = JTextField()
        var propertiesConsoleLoggerTextField4 = JTextField()
        var propertiesConsoleLoggerTextField5 = JTextField()
        var propertiesConsoleLoggerTextField6 = JTextField()
        var propertiesConsoleLoggerTextField7 = JTextField()
        var propertiesConsoleLoggerTextField8 = JTextField()
        var propertiesConsoleLoggerTextField9 = JTextField()
        var propertiesConsoleLoggerSaveButton = JButton()
        var propertiesConsoleLoggerLoad2Button = JButton()
        var propertiesConsoleLoggerLoad1Button = JButton()
        var propertiesConsoleLoggerCancelButton = JButton()

        var propertiesConsoleLoggerDefaultButton1 = JButton()
        var propertiesConsoleLoggerDefaultButton2 = JButton()
        var propertiesConsoleLoggerDefaultButton3 = JButton()
        var propertiesConsoleLoggerDefaultButton4 = JButton()
        var propertiesConsoleLoggerDefaultButton5 = JButton()
        var propertiesConsoleLoggerDefaultButton6 = JButton()
        var propertiesConsoleLoggerDefaultButton7 = JButton()
        var propertiesConsoleLoggerDefaultButton8 = JButton()
        var propertiesConsoleLoggerDefaultButton9 = JButton()

        var ui = DialogPanel()
    }

    constructor(
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
    ) : this() {
        Companion.propertiesConsoleLoggerTextField1 = propertiesConsoleLoggerTextField1
        Companion.propertiesConsoleLoggerTextField2 = propertiesConsoleLoggerTextField2
        Companion.propertiesConsoleLoggerTextField3 = propertiesConsoleLoggerTextField3
        Companion.propertiesConsoleLoggerTextField4 = propertiesConsoleLoggerTextField4
        Companion.propertiesConsoleLoggerTextField5 = propertiesConsoleLoggerTextField5
        Companion.propertiesConsoleLoggerTextField6 = propertiesConsoleLoggerTextField6
        Companion.propertiesConsoleLoggerTextField7 = propertiesConsoleLoggerTextField7
        Companion.propertiesConsoleLoggerTextField8 = propertiesConsoleLoggerTextField8
        Companion.propertiesConsoleLoggerTextField9 = propertiesConsoleLoggerTextField9

        Companion.propertiesConsoleLoggerSaveButton = propertiesConsoleLoggerSaveButton
        Companion.propertiesConsoleLoggerLoad2Button = propertiesConsoleLoggerLoad2Button
        Companion.propertiesConsoleLoggerLoad1Button = propertiesConsoleLoggerLoad1Button
        Companion.propertiesConsoleLoggerCancelButton = propertiesConsoleLoggerCancelButton

        Companion.propertiesConsoleLoggerDefaultButton1 = propertiesConsoleLoggerDefaultButton1
        Companion.propertiesConsoleLoggerDefaultButton2 = propertiesConsoleLoggerDefaultButton2
        Companion.propertiesConsoleLoggerDefaultButton3 = propertiesConsoleLoggerDefaultButton3
        Companion.propertiesConsoleLoggerDefaultButton4 = propertiesConsoleLoggerDefaultButton4
        Companion.propertiesConsoleLoggerDefaultButton5 = propertiesConsoleLoggerDefaultButton5
        Companion.propertiesConsoleLoggerDefaultButton6 = propertiesConsoleLoggerDefaultButton6
        Companion.propertiesConsoleLoggerDefaultButton7 = propertiesConsoleLoggerDefaultButton7
        Companion.propertiesConsoleLoggerDefaultButton8 = propertiesConsoleLoggerDefaultButton8
        Companion.propertiesConsoleLoggerDefaultButton9 = propertiesConsoleLoggerDefaultButton9
    }

    fun setup() {
        loadActiveLoggers()
        load1Loggers()

        propertiesConsoleLoggerDefaultButton1.addActionListener {
            propertiesConsoleLoggerTextField1.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_1
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_10
            }
        }
        propertiesConsoleLoggerDefaultButton2.addActionListener {
            propertiesConsoleLoggerTextField2.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_2
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_11
            }
        }
        propertiesConsoleLoggerDefaultButton3.addActionListener {
            propertiesConsoleLoggerTextField3.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_3
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_12
            }
        }
        propertiesConsoleLoggerDefaultButton4.addActionListener {
            propertiesConsoleLoggerTextField4.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_4
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_13
            }
        }
        propertiesConsoleLoggerDefaultButton5.addActionListener {
            propertiesConsoleLoggerTextField5.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_5
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_14
            }
        }
        propertiesConsoleLoggerDefaultButton6.addActionListener {
            propertiesConsoleLoggerTextField6.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_6
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_15
            }
        }
        propertiesConsoleLoggerDefaultButton7.addActionListener {
            propertiesConsoleLoggerTextField7.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_7
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_16
            }
        }
        propertiesConsoleLoggerDefaultButton8.addActionListener {
            propertiesConsoleLoggerTextField8.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_8
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_17
            }
        }
        propertiesConsoleLoggerDefaultButton9.addActionListener {
            propertiesConsoleLoggerTextField9.text = if (propertiesConsoleLoggerPreset == 1) {
                ConsoleLoggerSettings.DEFAULT_PATTERN_9
            } else {
                ConsoleLoggerSettings.DEFAULT_PATTERN_18
            }
        }

        propertiesConsoleLoggerCancelButton.addActionListener {
            when (propertiesConsoleLoggerPreset) {
                1 -> {
                    load1Loggers()
                    saveActiveLoggers()
                }
                2 -> {
                    load2Loggers()
                    saveActiveLoggers()
                }
            }
        }

        propertiesConsoleLoggerLoad1Button.addActionListener {
            load1Loggers()
            saveActiveLoggers()
        }

        propertiesConsoleLoggerLoad2Button.addActionListener {
            load2Loggers()
            saveActiveLoggers()
        }

        propertiesConsoleLoggerSaveButton.addActionListener {
            when (propertiesConsoleLoggerPreset) {
                1 -> {
                    saveActiveLoggers()
                    save1Loggers()
                }
                2 -> {
                    saveActiveLoggers()
                    save2Loggers()
                }
            }
        }
    }
    private fun loadActiveLoggers() {
        propertiesConsoleLoggerTextField1.text = ConsoleLoggerSettings.getPattern(0)
        propertiesConsoleLoggerTextField2.text = ConsoleLoggerSettings.getPattern(1)
        propertiesConsoleLoggerTextField3.text = ConsoleLoggerSettings.getPattern(2)
        propertiesConsoleLoggerTextField4.text = ConsoleLoggerSettings.getPattern(3)
        propertiesConsoleLoggerTextField5.text = ConsoleLoggerSettings.getPattern(4)
        propertiesConsoleLoggerTextField6.text = ConsoleLoggerSettings.getPattern(5)
        propertiesConsoleLoggerTextField7.text = ConsoleLoggerSettings.getPattern(6)
        propertiesConsoleLoggerTextField8.text = ConsoleLoggerSettings.getPattern(7)
        propertiesConsoleLoggerTextField9.text = ConsoleLoggerSettings.getPattern(8)
    }

    private fun saveActiveLoggers() {
        ConsoleLoggerSettings.setPattern(0, propertiesConsoleLoggerTextField1.text)
        ConsoleLoggerSettings.setPattern(1, propertiesConsoleLoggerTextField2.text)
        ConsoleLoggerSettings.setPattern(2, propertiesConsoleLoggerTextField3.text)
        ConsoleLoggerSettings.setPattern(3, propertiesConsoleLoggerTextField4.text)
        ConsoleLoggerSettings.setPattern(4, propertiesConsoleLoggerTextField5.text)
        ConsoleLoggerSettings.setPattern(5, propertiesConsoleLoggerTextField6.text)
        ConsoleLoggerSettings.setPattern(6, propertiesConsoleLoggerTextField7.text)
        ConsoleLoggerSettings.setPattern(7, propertiesConsoleLoggerTextField8.text)
        ConsoleLoggerSettings.setPattern(8, propertiesConsoleLoggerTextField9.text)
    }

    private fun save1Loggers() {
        ConsoleLoggerSettings.setPattern(9, propertiesConsoleLoggerTextField1.text)
        ConsoleLoggerSettings.setPattern(10, propertiesConsoleLoggerTextField2.text)
        ConsoleLoggerSettings.setPattern(11, propertiesConsoleLoggerTextField3.text)
        ConsoleLoggerSettings.setPattern(12, propertiesConsoleLoggerTextField4.text)
        ConsoleLoggerSettings.setPattern(13, propertiesConsoleLoggerTextField5.text)
        ConsoleLoggerSettings.setPattern(14, propertiesConsoleLoggerTextField6.text)
        ConsoleLoggerSettings.setPattern(15, propertiesConsoleLoggerTextField7.text)
        ConsoleLoggerSettings.setPattern(16, propertiesConsoleLoggerTextField8.text)
        ConsoleLoggerSettings.setPattern(17, propertiesConsoleLoggerTextField9.text)
    }

    private fun save2Loggers() {
        ConsoleLoggerSettings.setPattern(18, propertiesConsoleLoggerTextField1.text)
        ConsoleLoggerSettings.setPattern(19, propertiesConsoleLoggerTextField2.text)
        ConsoleLoggerSettings.setPattern(20, propertiesConsoleLoggerTextField3.text)
        ConsoleLoggerSettings.setPattern(21, propertiesConsoleLoggerTextField4.text)
        ConsoleLoggerSettings.setPattern(22, propertiesConsoleLoggerTextField5.text)
        ConsoleLoggerSettings.setPattern(23, propertiesConsoleLoggerTextField6.text)
        ConsoleLoggerSettings.setPattern(24, propertiesConsoleLoggerTextField7.text)
        ConsoleLoggerSettings.setPattern(25, propertiesConsoleLoggerTextField8.text)
        ConsoleLoggerSettings.setPattern(26, propertiesConsoleLoggerTextField9.text)
    }

    private fun load1Loggers() {
        propertiesConsoleLoggerPreset = 1
        propertiesConsoleLoggerTextField1.text = ConsoleLoggerSettings.getPattern(9)
        propertiesConsoleLoggerTextField2.text = ConsoleLoggerSettings.getPattern(10)
        propertiesConsoleLoggerTextField3.text = ConsoleLoggerSettings.getPattern(11)
        propertiesConsoleLoggerTextField4.text = ConsoleLoggerSettings.getPattern(12)
        propertiesConsoleLoggerTextField5.text = ConsoleLoggerSettings.getPattern(13)
        propertiesConsoleLoggerTextField6.text = ConsoleLoggerSettings.getPattern(14)
        propertiesConsoleLoggerTextField7.text = ConsoleLoggerSettings.getPattern(15)
        propertiesConsoleLoggerTextField8.text = ConsoleLoggerSettings.getPattern(16)
        propertiesConsoleLoggerTextField9.text = ConsoleLoggerSettings.getPattern(17)
    }

    private fun load2Loggers() {
        propertiesConsoleLoggerPreset = 2
        propertiesConsoleLoggerTextField1.text = ConsoleLoggerSettings.getPattern(18)
        propertiesConsoleLoggerTextField2.text = ConsoleLoggerSettings.getPattern(19)
        propertiesConsoleLoggerTextField3.text = ConsoleLoggerSettings.getPattern(20)
        propertiesConsoleLoggerTextField4.text = ConsoleLoggerSettings.getPattern(21)
        propertiesConsoleLoggerTextField5.text = ConsoleLoggerSettings.getPattern(22)
        propertiesConsoleLoggerTextField6.text = ConsoleLoggerSettings.getPattern(23)
        propertiesConsoleLoggerTextField7.text = ConsoleLoggerSettings.getPattern(24)
        propertiesConsoleLoggerTextField8.text = ConsoleLoggerSettings.getPattern(25)
        propertiesConsoleLoggerTextField9.text = ConsoleLoggerSettings.getPattern(26)
    }

    override fun reset(@NotNull settings: ConsoleLoggerSettings) {
        ui.reset()
    }

    override fun isModified(@NotNull settings: ConsoleLoggerSettings): Boolean {
        return ui.isModified()
    }

    override fun apply(@NotNull settings: ConsoleLoggerSettings) {
        ui.apply()
    }

    override fun getComponent(): JComponent {
        return ui
    }
}
