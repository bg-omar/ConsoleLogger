package com.github.bgomar.consolelogger.toolWindow

/**
 * [ComboBoxWithImageRenderer] item: PNG or SVG image + text.
 * @param title item's text
 * @param imagePath item's image path relative to the 'resources' folder, without leading `/`. Example: `consolelogger/image.svg`
 */
data class ComboBoxWithImageItem(val title: String, val imagePath: String) {

    fun displayName(): String {
        return title
    }
}
