package com.github.bgomar.consolelogger.chapters

import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.Disposable

@Service
class NavigatorPlugin : ApplicationComponent, Disposable {
    override fun dispose() {
        // Clean up resources or listeners when the plugin is disposed
        println("Chapter Script plugin disposed")
    }
}

/**
 * Listener for project-level events.
 */
@Service(Service.Level.PROJECT)
class ChapterProjectListener : ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Project opened: ${project.name}")
        // Additional initialization for the plugin when a project is opened
    }
}

/**
 * Handles startup activities when the IDE is loaded.
 */
/**
 * Handles tasks at project startup.
 */
class ChapterStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Chapter Script plugin startup activity for project: ${project.name}")
        // Assuming the initialization logic is handled elsewhere or not needed
    }
}