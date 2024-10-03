package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EditPresetAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(CommonDataKeys.PROJECT);
        if (project == null) {
            Messages.showErrorDialog("Project not found!", "Error");
            return;
        }

        // Simulate editing a preset file
        String projectDir = project.getBasePath();
        if (projectDir != null) {
            Path presetFile = Paths.get(projectDir, "preset-uploaded.txt");
            if (Files.exists(presetFile)) {
                try {
                    // Read the current content of the file
                    String content = new String(Files.readAllBytes(presetFile));
                    // Simulate editing by appending some text
                    String newContent = content + "\nEdited preset content.";

                    Files.write(presetFile, newContent.getBytes());

                    Messages.showInfoMessage("Preset edited successfully!", "Edit Preset");
                } catch (IOException ioException) {
                    Messages.showErrorDialog("Failed to edit preset: " + ioException.getMessage(), "Error");
                }
            } else {
                Messages.showErrorDialog("Preset file not found.", "Error");
            }
        } else {
            Messages.showErrorDialog("Project base path is null.", "Error");
        }
    }
}
