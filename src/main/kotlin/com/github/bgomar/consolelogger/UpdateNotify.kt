package com.github.bgomar.consolelogger

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.notification.NotificationType
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.github.bgomar.bgconsolelogger.tools.ConsoleLoggerSettings

class UpdateNotify : StartupActivity {
  private val plugin = PluginManagerCore.getPlugin(PluginId.getId("com.github.bgomar.consolelogger"))!!

  override fun runActivity(project: Project) {
    val settings = ConsoleLoggerSettings.getInstance();
    if (settings.version == "Unknown") {
      settings.version = plugin.version
    } else if (plugin.version != settings.version) {
      settings.version = plugin.version
      showUpdate(project)
    }
  }

  private val updateContent: String by lazy {
    //language=HTML
    """
     v0.0.22 added the Tools Window, and introduced different logo.<br/>
     In the Tools window you cannot yet adjust the settings of the loggers.<br/>
     Please keep in mind that this is a project I do to educate myself.<br/>
     <br/>
     v0.0.23 🤏 🐛 Couple of Bugfixes:  <br/>
     Fixed the issue with auto remove loggers (Ctrl + Alt + 0) <br/>
     Completed the ConsoleLogger Tool Window<br/>
     <br/>
    🦄 From now on you can <b>change Loggers<b> in Toolwindow<br>
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
