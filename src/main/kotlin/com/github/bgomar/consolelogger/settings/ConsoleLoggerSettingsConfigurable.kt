package com.github.bgomar.consolelogger.settings

import com.intellij.openapi.options.ConfigurableBase

class ConsoleLoggerSettingsConfigurable : ConfigurableBase<ConsoleLoggerConfigurableUI, ConsoleLoggerSettings>("com.github.bgomar.consolelogger", "ConsoleLogger", "") {

  override fun getSettings(): ConsoleLoggerSettings {
    return ConsoleLoggerSettings.instance
  }

  override fun createUi(): ConsoleLoggerConfigurableUI {
    return ConsoleLoggerConfigurableUI(settings)
  }
}
