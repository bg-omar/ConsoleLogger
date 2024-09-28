package com.github.bgomar.consolelogger

import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.notification.NotificationType
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class UpdateNotify : StartupActivity {
  private val plugin = PluginManagerCore.getPlugin(PluginId.getId("com.github.bgomar.consolelogger"))!!

  override fun runActivity(project: Project) {
    val settings = ConsoleLoggerSettings.getInstance();
    if (settings.version == "Unknown") {
      settings.version = plugin.version
      showUpdate(project)
    } else if (plugin.version != settings.version) {
      settings.version = plugin.version
      showUpdate(project)
    }
  }

  private val updateContent: String by lazy {
    //language=HTML
    """
    🐛 Bugfix for Saving 😁👌<br><br>
    Sorry it took so long, it was quite a hard task.<br>
    🤏 Small changes to defaults: <br> 
    - changed some of the input field sizes
    - added option for second preset of loggers
    """
  }

  private fun showUpdate(project: Project) {
    val notification = createNotification(
      "ConsoleLogger plugin updated to version ${plugin.version}",
      updateContent,
      NotificationType.INFORMATION
    )
    showFullNotification(project, notification)
  }
}
