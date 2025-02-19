package com.github.bgomar.consolelogger

import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications.*
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project


class ApplicationServicePlaceholder : Disposable {
  override fun dispose() = Unit

  companion object {
    val INSTANCE: ApplicationServicePlaceholder =
      ApplicationManager.getApplication().getService(ApplicationServicePlaceholder::class.java)
  }
}

fun createNotification(title: String, content: String, type: NotificationType): Notification {
  return NotificationGroupManager.getInstance()
    .getNotificationGroup("com.github.bgomar.consolelogger.notification")
    .createNotification(title, content, type)
}

fun showFullNotification(project: Project, notification: Notification) {
  // Directly notify the user through the Notification Bus
  Bus.notify(notification, project)
}