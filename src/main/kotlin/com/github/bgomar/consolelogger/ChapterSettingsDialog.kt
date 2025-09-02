package com.github.bgomar.consolelogger

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.*
import java.awt.GridBagLayout
import java.awt.GridBagConstraints
import java.awt.Insets

class ChapterSettingsDialog(
    initialChapter: String = "",
    initialSection: String = "",
    initialSubsection: String = "",
    initialChapterPatternName: String = "",
    initialSectionPatternName: String = "",
    initialSubsectionPatternName: String = ""
) : DialogWrapper(true) {
    val chapterTextField = JTextField(initialChapter, 30)
    val sectionTextField = JTextField(initialSection, 30)
    val subsectionTextField = JTextField(initialSubsection, 30)
    val chapterPatternNameTextField = JTextField(initialChapterPatternName, 15)
    val sectionPatternNameTextField = JTextField(initialSectionPatternName, 15)
    val subsectionPatternNameTextField = JTextField(initialSubsectionPatternName, 15)

    init {
        title = "Chapter Panel Settings"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(GridBagLayout())
        val gbc = GridBagConstraints()
        gbc.insets = Insets(5, 5, 5, 5)
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridx = 0
        gbc.gridy = 0
        panel.add(JLabel("Chapter:"), gbc)
        gbc.gridx = 1
        panel.add(chapterTextField, gbc)
        gbc.gridx = 2
        panel.add(chapterPatternNameTextField, gbc)

        gbc.gridy++
        gbc.gridx = 0
        panel.add(JLabel("Section:"), gbc)
        gbc.gridx = 1
        panel.add(sectionTextField, gbc)
        gbc.gridx = 2
        panel.add(sectionPatternNameTextField, gbc)

        gbc.gridy++
        gbc.gridx = 0
        panel.add(JLabel("Subsection:"), gbc)
        gbc.gridx = 1
        panel.add(subsectionTextField, gbc)
        gbc.gridx = 2
        panel.add(subsectionPatternNameTextField, gbc)

        return panel
    }

    fun getChapter() = chapterTextField.text
    fun getSection() = sectionTextField.text
    fun getSubsection() = subsectionTextField.text
    fun getChapterPatternName() = chapterPatternNameTextField.text
    fun getSectionPatternName() = sectionPatternNameTextField.text
    fun getSubsectionPatternName() = subsectionPatternNameTextField.text
}