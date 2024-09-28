package com.github.bgomar.bgconsolelogger.toolwindow;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ConsoleLoggerToolWindowFactory implements ToolWindowFactory, DumbAware {

    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ConsoleLoggerToolWindow myToolWindow = null;
        try {
            myToolWindow = new ConsoleLoggerToolWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(myToolWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
