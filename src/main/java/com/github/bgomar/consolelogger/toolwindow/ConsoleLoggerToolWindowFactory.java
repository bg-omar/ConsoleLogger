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
        // Get settings instance
        ConsoleLoggerSettings settings = ConsoleLoggerSettings.getInstance();
//        ConsoleLoggerSettings.loadSettings();
        // Create a custom component or panel based on settings
        ConsoleLoggerToolWindow toolWindowPanel = new ConsoleLoggerToolWindow(settings);

        // Use the new method to get ContentFactory instance
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(toolWindowPanel.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);


//        ConsoleLoggerToolWindowPanel myToolWindow = null;
//        try {
//            myToolWindow = new ConsoleLoggerToolWindowPanel();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        ConsoleLoggerSettings.loadSettings();
//        ContentFactory contentFactory = ContentFactory.getInstance();
//        Content content = contentFactory.createContent(myToolWindow.getContent(), "", false);
//        toolWindow.getContentManager().addContent(content);
    }
}
