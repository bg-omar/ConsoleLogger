package com.github.bgomar.consolelogger.tools;

import com.github.bgomar.consolelogger.toolwindow.setup.PropertiesConsoleLoggerToolSetup;
import com.intellij.openapi.options.ConfigurableBase;
import org.jetbrains.annotations.NotNull;

public class ConsoleLoggerSettingsConfigurable extends ConfigurableBase<PropertiesConsoleLoggerToolSetup, ConsoleLoggerSettings> {

  public ConsoleLoggerSettingsConfigurable() {
    super("com.github.bgomar.consolelogger", "ConsoleLogger", "");
  }

  @Override
  protected @NotNull ConsoleLoggerSettings getSettings() {
    return ConsoleLoggerSettings.getInstance();
  }

  @Override
  protected PropertiesConsoleLoggerToolSetup createUi() {
    return new ConsoleLoggerConfigurableUI.ConsoleLoggerConfig(getSettings());
  }
}