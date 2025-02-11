package com.github.bgomar.bgconsolelogger.chapters

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project


/**
 * Main service to manage plugin-wide functionality for the Chapter Script plugin.
 */
@Service(Service.Level.PROJECT)
class ChapterNavigatorPlugin {
    init {
        println("✅ ChapterNavigatorPlugin initialized")
    }

    companion object {
        fun getInstance(project: Project): ChapterNavigatorPlugin? {
            val app = com.intellij.openapi.application.ApplicationManager.getApplication()
            return if (app != null) {
                project.service<ChapterNavigatorPlugin>() // ✅ Safe service access
            } else {
                println("❌ Error: Cannot retrieve ChapterNavigatorPlugin because ApplicationManager is null!")
                null
            }
        }
    }

    /**
     * Perform any project-level initialization logic here.
     */
    fun initializeForProject(project: Project) {
        println("✅ Initializing Chapter Script plugin for project: ${project.name}")
    }

    /**
     * Clean up resources for the plugin, if necessary.
     */
    fun disposeForProject(project: Project) {
        println("Disposing Chapter Script plugin for project: ${project.name}")
    }
}