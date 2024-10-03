package com.github.bgomar.consolelogger

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException

class PresetDeployer(private val project: Project) {

    // Method to deploy a preset file by applying its content
    fun deployPreset(presetFileName: String) {
        val rootDir = project.baseDir ?: return
        val presetFile = rootDir.findChild(presetFileName) ?: return

        try {
            // Read preset content and perform any deployment logic
            val content = String(presetFile.contentsToByteArray())
            applyPreset(content)
        } catch (e: IOException) {
            notifyUser("Failed to deploy preset: ${e.message}")
        }
    }

    // Method to apply the preset content (this can be customized)
    private fun applyPreset(presetContent: String) {
        // Perform actions based on the content of the preset file
        println("Applying preset: $presetContent")
        notifyUser("Preset applied successfully.")
    }

    // Method to notify the user in the IDE
    private fun notifyUser(message: String) {
        ApplicationManager.getApplication().invokeLater {
            com.intellij.notification.NotificationGroupManager.getInstance()
                .getNotificationGroup("com.github.bgomar.consolelogger")
                .createNotification(message, com.intellij.notification.NotificationType.INFORMATION)
                .notify(project)
        }
    }
}
