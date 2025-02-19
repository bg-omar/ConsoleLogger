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
        if (plugin.version == "Unknown" || plugin.version != settings.version) {
            settings.version = plugin.version
            showUpdate(project)
        } else if (plugin.version == settings.version) {
            settings.version = plugin.version
        }
    }

    private fun showUpdate(project: Project) {
        ApplicationManager.getApplication().invokeLater {
            val notification = createNotification(
                "ConsoleLogger plugin updated to version ${plugin.version}",
                """
            🐛 Bugfix for Saving 😁👌<br><br>
            Sorry it took so long, it was quite a hard task.<br>
            - ✅Added ChapterTool, to create chapters in your code <br>
            - Double-click on the chapter to jump to the line <br>
            - ✅Function Obfuscator shows in ToolWindow <br>
            - Fixed the issue with the logline numbers <br>
            🤏 Small changes to defaults: <br>
            - Changed input field sizes <br>
            - Fixed Issue with Code Insertion Position  <br>
            - Added the Re-check line numbers function <br>
            - Fixed issue with the removal of ConsoleLogger logs <br>
            """,
                NotificationType.INFORMATION
            )
            showFullNotification(project, notification)
        }
    }
}