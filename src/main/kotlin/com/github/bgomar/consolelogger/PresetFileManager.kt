package com.github.bgomar.consolelogger

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException

class PresetFileManager(private val project: Project) {

    // Method to save a preset file to the root directory
    fun savePreset(presetFileName: String, content: ByteArray) {
        val rootDir = project.baseDir ?: return
        val presetFile = rootDir.findChild(presetFileName)

        WriteCommandAction.runWriteCommandAction(project) {
            try {
                if (presetFile != null) {
                    // Overwrite existing preset file
                    presetFile.setBinaryContent(content)
                } else {
                    // Create a new preset file
                    val newFile = rootDir.createChildData(this, presetFileName)
                    newFile.setBinaryContent(content)
                }
                notifyUser("Preset file saved as ${rootDir.path}/$presetFileName")
            } catch (e: IOException) {
                notifyUser("Failed to save preset file: ${e.message}")
            }
        }
    }

    // Method to upload a preset file (e.g., from a file chooser)
    fun uploadPreset(uploadedFile: VirtualFile) {
        val presetFileName = uploadedFile.name
        val content = uploadedFile.contentsToByteArray()

        savePreset(presetFileName, content)
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
