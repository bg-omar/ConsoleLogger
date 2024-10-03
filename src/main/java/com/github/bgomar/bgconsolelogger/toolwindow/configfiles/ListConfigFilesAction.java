package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListConfigFilesAction extends AnAction {

    public ListConfigFilesAction() {
        super("List Config Files");
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(CommonDataKeys.PROJECT);
        if (project == null) {
            return;
        }

        // Get the root directory of the project
        VirtualFile rootDir = LocalFileSystem.getInstance().findFileByPath(Objects.requireNonNull(project.getBasePath()));
        if (rootDir == null) {
            Messages.showMessageDialog(project, "Root directory not found", "Error", Messages.getErrorIcon());
            return;
        }

        // Define the directory where the config files are stored
        VirtualFile configDir = rootDir;
        if (configDir == null || !configDir.isDirectory()) {
            Messages.showMessageDialog(project, "Config directory not found", "Error", Messages.getErrorIcon());
            return;
        }

        // List all config files
        List<String> configFileNames = listConfigFiles(configDir);

        // Display the list of config files in a dialog
        if (configFileNames.isEmpty()) {
            Messages.showMessageDialog(project, "No config files found", "Info", Messages.getInformationIcon());
        } else {
            StringBuilder fileList = new StringBuilder("Config Files:\n\n");
            for (String fileName : configFileNames) {
                fileList.append(fileName).append("\n");
            }
            Messages.showMessageDialog(project, fileList.toString(), "Config Files", Messages.getInformationIcon());
        }
    }

    /**
     * Lists all the config files in the specified directory.
     */
    private List<String> listConfigFiles(VirtualFile configDir) {
        List<String> configFileNames = new ArrayList<>();
        for (VirtualFile file : configDir.getChildren()) {
            if (!file.isDirectory() && file.getExtension() != null && file.getExtension().equals("txt")) {
                configFileNames.add(file.getName());
            }
        }
        return configFileNames;
    }
}
