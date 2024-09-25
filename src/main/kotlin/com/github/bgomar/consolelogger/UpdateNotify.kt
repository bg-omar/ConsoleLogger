package com.github.bgomar.consolelogger

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.notification.NotificationType
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.github.bgomar.consolelogger.settings.ConsoleLoggerSettings

class UpdateNotify : StartupActivity {
  private val plugin = PluginManagerCore.getPlugin(PluginId.getId("com.github.bgomar.consolelogger"))!!

  override fun runActivity(project: Project) {
    val settings = ConsoleLoggerSettings.instance
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
    🐛 Bugfix for (ctrl + alt + 1/2 ) <br/>
    🤏 Small changes to defaults: <br> 
    4 = Arduino Serial.print() <br> 
    5 = C++ cout << "" << endl;<br> 
    6 = Python print("{:>30}".format()) 😁👌<br><br>

    You can always <b>change defaults<b> in settings (ctrl + alt + S)<br>
    <b>Settings | Tools | ConsoleLogger</b> 
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
