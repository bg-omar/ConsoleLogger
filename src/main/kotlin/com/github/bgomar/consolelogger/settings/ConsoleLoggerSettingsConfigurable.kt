package com.github.bgomar.consolelogger.settings

import com.github.bgomar.consolelogger.settings.ConsoleLoggerConfigurableUI
import com.intellij.openapi.options.ConfigurableBase
import com.intellij.openapi.options.ConfigurableUi

class ConsoleLoggerSettingsConfigurable : ConfigurableBase<ConfigurableUi<ConsoleLoggerSettings!>, ConsoleLoggerSettings>("com.github.bgomar.consolelogger", "ConsoleLogger", "") {

  override fun getSettings(): ConsoleLoggerSettings {
    return ConsoleLoggerSettings.instance
  }

  override fun createUi(): ConsoleLoggerConfigurableUI {
    return ConsoleLoggerConfigurableUI(settings)
  }
}