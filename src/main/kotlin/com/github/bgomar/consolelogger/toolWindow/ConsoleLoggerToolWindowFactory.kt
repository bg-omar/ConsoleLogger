package com.github.bgomar.consolelogger.toolWindow

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class ConsoleLoggerToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: com.intellij.openapi.project.Project, toolWindow: ToolWindow) {
        val loggerToolWindow = ConsoleLoggerToolWindow(toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(loggerToolWindow.getContent(), "", false)
        toolWindow.contentManager.addContent(content)
    }
}
