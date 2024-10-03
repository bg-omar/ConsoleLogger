package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ConfigFileAction {

    public ConfigFileAction() {
        super();
    }


    // Create a config file in the root directory
    public void createConfigFile(Project project, VirtualFile rootDir, String configFileName, String content) throws
            IOException {
        WriteCommandAction.runWriteCommandAction(project, () -> {
            try {
                VirtualFile newFile = rootDir.createChildData(this, configFileName);
                newFile.setBinaryContent(content.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to create config file: " + e.getMessage());
            }
        });
    }

    // Modify the config file
    public void modifyConfigFile(Project project, VirtualFile rootDir, String configFileName, String newContent) throws
            IOException {
        VirtualFile configFile = rootDir.findChild(configFileName);
        if (configFile != null) {
            WriteCommandAction.runWriteCommandAction(project, () -> {
                try {
                    configFile.setBinaryContent(newContent.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to modify config file: " + e.getMessage());
                }
            });
        } else {
            throw new IOException("Config file not found: " + configFileName);
        }
    }

    // Delete the config file
    public void deleteConfigFile(Project project, VirtualFile rootDir, String configFileName) throws IOException {
        VirtualFile configFile = rootDir.findChild(configFileName);
        if (configFile != null) {
            WriteCommandAction.runWriteCommandAction(project, () -> {
                try {
                    configFile.delete(this);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to delete config file: " + e.getMessage());
                }
            });
        } else {
            throw new IOException("Config file not found: " + configFileName);
        }
    }
}

