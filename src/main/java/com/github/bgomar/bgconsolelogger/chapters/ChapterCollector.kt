package com.github.bgomar.bgconsolelogger.chapters

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiFile

object ChapterCollector {
    @JvmStatic  // ✅ Makes it accessible from Java
    fun collectChapters(file: PsiFile): List<Chapter> {
        val chapters = mutableListOf<Chapter>()
        val document: Document = file.viewProvider.document ?: return chapters

        // ✅ Dynamically get CHAPTER_PATTERN from ConsoleLoggerSettings
        val chapterPattern = ConsoleLoggerSettings.getPattern(27).trim()
        val sectionPattern = ConsoleLoggerSettings.getPattern(28).trim()
        val subsectionPattern = ConsoleLoggerSettings.getPattern(29).trim()

        // Fetch pattern names dynamically
        val chapterPatternName = ConsoleLoggerSettings.getPattern(30).trim()
        val sectionPatternName = ConsoleLoggerSettings.getPattern(31).trim()
        val subsectionPatternName = ConsoleLoggerSettings.getPattern(32).trim()

        val lines = document.text.split("\n")
        for ((index, line) in lines.withIndex()) {
            val trimmedLine = line.trim()
            when {
                chapterPattern.isNotEmpty() && trimmedLine.startsWith(chapterPattern) -> {
                    val title = trimmedLine.removePrefix(chapterPattern).trim()
                    chapters.add(Chapter("$chapterPatternName $title", index + 1, Chapter.Type.CHAPTER))
                }
                sectionPattern.isNotEmpty() && trimmedLine.startsWith(sectionPattern) -> {
                    val title = trimmedLine.removePrefix(sectionPattern).trim()
                    chapters.add(Chapter("$sectionPatternName $title", index + 1, Chapter.Type.SECTION))
                }
                subsectionPattern.isNotEmpty() && trimmedLine.startsWith(subsectionPattern) -> {
                    val title = trimmedLine.removePrefix(subsectionPattern).trim()
                    chapters.add(Chapter("$subsectionPatternName $title", index + 1, Chapter.Type.SUBSECTION))
                }
            }
        }
        return chapters
    }
}