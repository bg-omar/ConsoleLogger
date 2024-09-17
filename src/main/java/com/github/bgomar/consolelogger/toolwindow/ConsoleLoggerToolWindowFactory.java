package com.github.bgomar.consolelogger.toolwindow;

import com.github.bgomar.consolelogger.tools.ConsoleLoggerSettings;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ConsoleLoggerToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, ToolWindow toolWindow) {
        ConsoleLoggerToolWindow toolWindowPanel = null;

        // Get settings instance
        ConsoleLoggerSettings settings = ConsoleLoggerSettings.getInstance();

        // Create a custom component or panel based on settings
        toolWindowPanel = new ConsoleLoggerToolWindow(settings);

        // Use the new method to get ContentFactory instance
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(toolWindowPanel.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);

    }
}
