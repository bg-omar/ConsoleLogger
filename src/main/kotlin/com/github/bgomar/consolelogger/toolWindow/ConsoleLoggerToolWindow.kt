package com.github.bgomar.consolelogger.toolWindow

import com.github.bgomar.consolelogger.toolWindow.setup.PropertiesConsoleLoggerToolSetup
import com.github.bgomar.consolelogger.toolWindow.setup.Px2RemToolSetup
import com.github.bgomar.consolelogger.toolWindow.setup.URLCodecToolSetup
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.ComboboxSpeedSearch
import com.intellij.ui.components.JBTextField
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JTextPane


class ConsoleLoggerToolWindow(settings: ToolWindow) {

    private lateinit var mainPanel: JPanel
    private lateinit var toolComboBox: JComboBox<ComboBoxWithImageItem>
    private lateinit var helpLabel: JLabel

    private lateinit var urlCodecPanel: JPanel
    private lateinit var urlCodecDecodedTextArea: JBTextField
    private lateinit var urlCodecEncodedTextArea: JBTextField
    private lateinit var urlCodecSvg2CssTextArea: JBTextField

    private lateinit var px2RemPanel: JPanel
    private lateinit var px2RemTextField: JBTextField
    private lateinit var rem2PxTextField: JBTextField

    private lateinit var propertiesConsoleLoggerPanel: JPanel
    private lateinit var propertiesConsoleLoggerTextField1: JTextField
    private lateinit var propertiesConsoleLoggerTextField2: JTextField
    private lateinit var propertiesConsoleLoggerTextField3: JTextField
    private lateinit var propertiesConsoleLoggerTextField4: JTextField
    private lateinit var propertiesConsoleLoggerTextField5: JTextField
    private lateinit var propertiesConsoleLoggerTextField6: JTextField
    private lateinit var propertiesConsoleLoggerTextField7: JTextField
    private lateinit var propertiesConsoleLoggerTextField8: JTextField
    private lateinit var propertiesConsoleLoggerTextField9: JTextField

    private lateinit var propertiesConsoleLoggerDefaultButton1: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton2: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton3: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton4: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton5: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton6: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton7: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton8: JButton
    private lateinit var propertiesConsoleLoggerDefaultButton9: JButton

    private lateinit var cancelAllButton: JButton
    private lateinit var saveButton: JButton
    private lateinit var load2Button: JButton
    private lateinit var load1Button: JButton
    private lateinit var preview: JTextPane

    private val toolPanelsByTitle: java.util.LinkedHashMap<String, PanelAndIcon> = LinkedHashMap()

    private data class PanelAndIcon(val panel: JPanel, val icon: String)

    init {
        // Create JTextFields and Buttons for the UI

    }
    init {

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
        mainPanel = ui.getComponent() as JPanel

        val iconsPath = "icons/"

        toolPanelsByTitle["Properties of ConsoleLogger"] =
            PanelAndIcon(propertiesConsoleLoggerPanel, iconsPath + "cryingcatt.svg")
        toolPanelsByTitle["Pixels to REM"] =
            PanelAndIcon(px2RemPanel, iconsPath + "coolcat.svg")
        toolPanelsByTitle["Svg 2 Css"] =
            PanelAndIcon(urlCodecPanel, iconsPath + "devcat.svg")

        PropertiesConsoleLoggerToolSetup(
            propertiesConsoleLoggerTextField1,
            propertiesConsoleLoggerTextField2,
            propertiesConsoleLoggerTextField3,
            propertiesConsoleLoggerTextField4,
            propertiesConsoleLoggerTextField5,
            propertiesConsoleLoggerTextField6,
            propertiesConsoleLoggerTextField7,
            propertiesConsoleLoggerTextField8,
            propertiesConsoleLoggerTextField9,
            saveButton,
            load2Button,
            load1Button,
            cancelAllButton,
            propertiesConsoleLoggerDefaultButton1,
            propertiesConsoleLoggerDefaultButton2,
            propertiesConsoleLoggerDefaultButton3,
            propertiesConsoleLoggerDefaultButton4,
            propertiesConsoleLoggerDefaultButton5,
            propertiesConsoleLoggerDefaultButton6,
            propertiesConsoleLoggerDefaultButton7,
            propertiesConsoleLoggerDefaultButton8,
            propertiesConsoleLoggerDefaultButton9
        ).setup()


        URLCodecToolSetup(
            urlCodecDecodedTextArea,
            urlCodecEncodedTextArea,
            urlCodecSvg2CssTextArea,
            preview
        ).setup()

        Px2RemToolSetup(
            px2RemTextField,
            rem2PxTextField
        ).setup()

        toolPanelsByTitle.forEach { (title, panelAndIcon) ->
            toolComboBox.addItem(ComboBoxWithImageItem(title, panelAndIcon.icon))
        }

        toolComboBox.renderer = ComboBoxWithImageRenderer()
        toolComboBox.maximumRowCount = 11
        ComboboxSpeedSearch.installSpeedSearch(toolComboBox) { item -> item.displayName() }
        helpLabel.text = ""
        helpLabel.icon = IconLoader.getIcon(iconsPath + "contextHelp.svg", ConsoleLoggerToolWindow::class.java)
        helpLabel.toolTipText = ""
        helpLabel.isVisible = false

        toolComboBox.addActionListener {
            val item = toolComboBox.getItemAt(toolComboBox.selectedIndex)
            displayToolPanel(item.title)
        }
        toolComboBox.selectedIndex = 0
    }

        private fun displayToolPanel(toolPanelTitle: String) {
            toolPanelsByTitle.forEach { (_, jPanel) -> jPanel.panel.isVisible = false }
            toolPanelsByTitle[toolPanelTitle]?.panel?.isVisible = true
        }

        fun getComponent(): JPanel {
            return mainPanel
        }

    fun getContent(): JPanel {
        return mainPanel
    }
}
