package com.github.bgomar.consolelogger.chapters

import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.startup.StartupActivity
import org.jetbrains.annotations.NotNull
import com.intellij.openapi.startup.ProjectActivity
/**
 * Main plugin component to handle initialization or global plugin settings.
 */
@Service
class NavigatorPlugin : ApplicationComponent {
    @Deprecated("Deprecated in Java")
    override fun initComponent() {
        // Initialize any plugin-wide settings or log messages
        println("Chapter Script plugin initialized")
    }

    @Deprecated("Deprecated in Java")
    override fun disposeComponent() {
        // Clean up resources or listeners when the plugin is disposed
        println("Chapter Script plugin disposed")
    }

    override fun getComponentName(): String {
        return "ChapterNavigatorPlugin"
    }
}

/**
 * Listener for project-level events.
 */
@Service(Service.Level.PROJECT)
class ChapterProjectListener : ProjectManagerListener {
    override fun projectOpened(project: Project) {
        println("Project opened: ${project.name}")
        // Additional initialization for the plugin when a project is opened
    }

    override fun projectClosed(project: Project) {
        println("Project closed: ${project.name}")
        // Handle cleanup or other project-specific events
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
        ChapterNavigatorPlugin.getInstance(project).initializeForProject(project)
    }
}