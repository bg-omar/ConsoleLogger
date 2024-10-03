package com.github.bgomar.bgconsolelogger.toolwindow.configfiles;



import com.github.bgomar.bgconsolelogger.toolwindow.configfiles.ConfigFileAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ConfigFileDialog extends JDialog {

    private JTextField configFileNameField;
    private JTextArea configFileContentArea;

    private JButton deployButton;
    private JButton editButton;
    private JButton deleteButton;

    private final ConfigFileAction configFileAction;
    private final Project project;

    public ConfigFileDialog(Project project) {
        this.project = project;
        this.configFileAction = new ConfigFileAction();

        setTitle("Manage Config Files");
        setModal(true);
        setLayout(new BorderLayout());

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        configFileNameField = new JTextField(20);
        configFileContentArea = new JTextArea(5, 20);
        inputPanel.add(new JLabel("Config File Name:"));
        inputPanel.add(configFileNameField);
        inputPanel.add(new JLabel("Config Content:"));
        inputPanel.add(new JScrollPane(configFileContentArea));

        add(inputPanel, BorderLayout.CENTER);

        // Buttons for actions
        JPanel buttonPanel = new JPanel(new FlowLayout());
        deployButton = new JButton("Deploy");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        buttonPanel.add(deployButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add button listeners for actions
        deployButton.addActionListener(e -> deployConfig());
        editButton.addActionListener(e -> editConfig());
        deleteButton.addActionListener(e -> deleteConfig());

        pack();
        setLocationRelativeTo(null);  // Center the dialog on screen
    }

    // Method to deploy a new config file using ConfigFileAction
    private void deployConfig() {
        String configFileName = configFileNameField.getText();
        String configContent = configFileContentArea.getText();

        if (configFileName.isEmpty() || configContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Config file name and content cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Call create method from ConfigFileAction
        try {
            configFileAction.createConfigFile(project, LocalFileSystem.getInstance().findFileByPath(project.getBasePath()), configFileName, configContent);
            JOptionPane.showMessageDialog(this, "Config file deployed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to deploy config file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to edit the config file using ConfigFileAction
    private void editConfig() {
        String configFileName = configFileNameField.getText();
        String configContent = configFileContentArea.getText();

        if (configFileName.isEmpty() || configContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Config file name and content cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Call edit method from ConfigFileAction
        try {
            configFileAction.modifyConfigFile(project, LocalFileSystem.getInstance().findFileByPath(project.getBasePath()), configFileName, configContent);
            JOptionPane.showMessageDialog(this, "Config file edited successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to edit config file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete the config file using ConfigFileAction
    private void deleteConfig() {
        String configFileName = configFileNameField.getText();

        if (configFileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Config file name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Call delete method from ConfigFileAction
        try {
            configFileAction.deleteConfigFile(project, LocalFileSystem.getInstance().findFileByPath(project.getBasePath()), configFileName);
            JOptionPane.showMessageDialog(this, "Config file deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete config file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
