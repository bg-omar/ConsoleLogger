package com.github.bgomar.consolelogger
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class PresetFileUploader(private val project: Project) {

    fun chooseAndUploadPreset() {
        val descriptor = FileChooserDescriptor(true, false, false, false, false, false)
        val chosenFile: VirtualFile? = FileChooser.chooseFile(descriptor, project, null)

        if (chosenFile != null) {
            val presetManager = PresetFileManager(project)
            presetManager.uploadPreset(chosenFile)
        }
    }
}
