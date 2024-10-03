package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DeletePresetAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            Messages.showErrorDialog("Project not found!", "Error");
            return;
        }

        // Simulate deleting a preset file
        String projectDir = project.getBasePath();
        if (projectDir != null) {
            Path presetFile = Paths.get(projectDir, "preset-uploaded.txt");
            if (Files.exists(presetFile)) {
                try {
                    Files.delete(presetFile);
                    Messages.showInfoMessage("Preset deleted successfully!", "Delete Preset");
                } catch (IOException ioException) {
                    Messages.showErrorDialog("Failed to delete preset: " + ioException.getMessage(), "Error");
                }
            } else {
                Messages.showErrorDialog("Preset file not found.", "Error");
            }
        } else {
            Messages.showErrorDialog("Project base path is null.", "Error");
        }
    }
}
