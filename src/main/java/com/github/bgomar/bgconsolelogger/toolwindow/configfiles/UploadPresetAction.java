package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadPresetAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(CommonDataKeys.PROJECT);
        if (project == null) {
            Messages.showErrorDialog("Project not found!", "Error");
            return;
        }

        // Simulate file upload functionality
        String projectDir = project.getBasePath();
        if (projectDir != null) {
            Path presetFile = Paths.get(projectDir, "config.txt");
            try {
                Files.createFile(presetFile);
                Files.write(presetFile, "This is a sample uploaded preset.".getBytes());
                Messages.showInfoMessage("Preset uploaded successfully!", "Upload Preset");
            } catch (IOException ioException) {
                Messages.showErrorDialog("Failed to upload preset: " + ioException.getMessage(), "Error");
            }
        } else {
            Messages.showErrorDialog("Project base path is null.", "Error");
        }
    }
}
