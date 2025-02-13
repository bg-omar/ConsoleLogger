package com.github.bgomar.bgconsolelogger.chapters

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

/**
 * Handles tasks at project startup.
 */
class ChapterStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Chapter Script plugin startup activity for project: ${project.name}")
        // Assuming the initialization logic is handled elsewhere or not needed
    }
}