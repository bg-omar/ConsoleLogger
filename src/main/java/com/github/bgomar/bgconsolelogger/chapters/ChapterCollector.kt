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

        val lines = document.text.split("\n")
        for ((index, line) in lines.withIndex()) {
            val trimmedLine = line.trim()
            if (trimmedLine.startsWith(chapterPattern)) {
                val title = trimmedLine.removePrefix(chapterPattern).trim()
                chapters.add(Chapter(title, index + 1)) // Line numbers start from 1
            }
        }
        return chapters
    }
}