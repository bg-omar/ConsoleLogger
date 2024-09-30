package com.github.bgomar.consolelogger

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
    if (settings.version == "Unknown") {
      settings.version = plugin.version
      showUpdate(project)
    } else if (plugin.version != settings.version) {
      settings.version = plugin.version
      showUpdate(project)
    }
  }

  private fun showUpdate(project: Project) {
    val notification = createNotification(
      "ConsoleLogger plugin updated to version ${plugin.version}",
      """
            🐛 Bugfix for Saving 😁👌<br><br>
            Sorry it took so long, it was quite a hard task.<br>
            🤏 Small changes to defaults: <br>
            - Changed input field sizes<br>
            - Added second logger preset option<br>
            - Fixed Issue with Code Insertion Position and <br>
              Log Line Number Updates in Plugin<br>
            - Added the Re-check line numbers function<br>
            """,
      NotificationType.INFORMATION
    )
    showFullNotification(project, notification)
  }
}

