package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeployPresetAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            Messages.showErrorDialog("Project not found!", "Error");
            return;
        }

        // Simulate deploying a preset by reading a file from the project directory
        String projectDir = project.getBasePath();
        if (projectDir != null) {
            Path presetFile = Paths.get(projectDir, "preset-uploaded.txt");
            if (Files.exists(presetFile)) {
                Messages.showInfoMessage("Preset deployed successfully from " + presetFile.toString(), "Deploy Preset");
            } else {
                Messages.showErrorDialog("Preset file not found.", "Error");
            }
        } else {
            Messages.showErrorDialog("Project base path is null.", "Error");
        }
    }
}
