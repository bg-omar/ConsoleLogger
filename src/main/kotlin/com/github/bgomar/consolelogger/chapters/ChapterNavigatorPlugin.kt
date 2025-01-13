package com.github.bgomar.consolelogger.chapters


import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import org.jetbrains.annotations.NotNull

/**
 * Main service to manage plugin-wide functionality for the Chapter Script plugin.
 */
@Service
class ChapterNavigatorPlugin {
    init {
        println("ChapterNavigatorPlugin initialized")
    }

    companion object {
        /**
         * Get the instance of the plugin service for the given project.
         */
        fun getInstance(project: Project): ChapterNavigatorPlugin =
            project.getService(ChapterNavigatorPlugin::class.java)
    }

    /**
     * Perform any project-level initialization logic here.
     */
    fun initializeForProject(project: Project) {
        println("Initializing Chapter Script plugin for project: ${project.name}")
    }

    /**
     * Clean up resources for the plugin, if necessary.
     */
    fun disposeForProject(project: Project) {
        println("Disposing Chapter Script plugin for project: ${project.name}")
    }
}

/**
 * Handles tasks at project startup.
 */
class ChapterNavigatorStartupActivity : StartupActivity {
    override fun runActivity(@NotNull project: Project) {
        println("ChapterNavigatorPlugin startup activity for project: ${project.name}")
        ChapterNavigatorPlugin.getInstance(project).initializeForProject(project)
    }
}
