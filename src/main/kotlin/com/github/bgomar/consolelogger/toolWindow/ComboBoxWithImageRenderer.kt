package com.github.bgomar.consolelogger.toolWindow

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.IconLoader
import java.awt.Component
import javax.swing.*

/**
 * A [JComboBox] renderer with PNG or SVG image + text.
 * Example:
 * ```
 * myComboBox.setRenderer(ComboBoxWithImageRenderer())
 * myComboBox.addItem(ComboBoxWithImageItem("first item", "consolelogger/first_item.svg"))
 * ```
 */
class ComboBoxWithImageRenderer : JLabel(), ListCellRenderer<Any> {

    companion object {
        private val LOGGER = Logger.getInstance(ComboBoxWithImageRenderer::class.java)
    }

    init {
        isOpaque = true
        horizontalAlignment = LEFT
        verticalAlignment = CENTER
    }

    override fun getListCellRendererComponent(
        list: JList<out Any>,
        value: Any?,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        if (isSelected) {
            background = list.selectionBackground
            foreground = list.selectionForeground
        } else {
            background = list.background
            foreground = list.foreground
        }

        val item = value as? ComboBoxWithImageItem ?: return this
        text = item.title

        try {
            icon = IconLoader.getIcon(item.imagePath, ComboBoxWithImageRenderer::class.java)
        } catch (e: Exception) {
            icon = null
            LOGGER.warn("Failed to load Tool icon '$item'", e)
        }

        iconTextGap = 6
        return this
    }
}
