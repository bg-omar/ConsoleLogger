package com.github.bgomar.consolelogger

import com.intellij.openapi.application.ApplicationManager
import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.notification.NotificationType
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class UpdateNotify : ProjectActivity {

    private val plugin = PluginManagerCore.getPlugin(PluginId.getId("com.github.bgomar.consolelogger"))!!

    override suspend fun execute(project: Project) {
        val settings = ConsoleLoggerSettings.getInstance()
//        print("Plugin version: ")
//        print(plugin.version)
//        print("Settings version: ")
//        println(settings.version)
        if (plugin.version == settings.version) {
            return  // already up to date, skip
        }
        settings.version = plugin.version
        showUpdate(project)
    }

    private fun showUpdate(project: Project) {
        ApplicationManager.getApplication().invokeLater {
            val notification = createNotification(
                "ConsoleLogger plugin updated to version ${plugin.version}",
                """
                removed depricated features and fixed bugs.
                """,
                NotificationType.INFORMATION
            )
            showFullNotification(project, notification)
        }
    }
}