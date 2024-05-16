package com.github.bgomar.bgconsolelogger.tools;

import com.github.bgomar.bgconsolelogger.toolwindow.setup.PropertiesConsoleLoggerToolSetup;
import com.intellij.openapi.options.ConfigurableBase;
import org.jetbrains.annotations.NotNull;

public class bgConsoleLoggerSettingsConfigurable extends ConfigurableBase<PropertiesConsoleLoggerToolSetup, bgConsoleLoggerSettings> {

  public bgConsoleLoggerSettingsConfigurable() {
    super("com.github.bgomar.bgconsolelogger", "BgConsoleLogger", "");
  }

  @Override
  protected @NotNull bgConsoleLoggerSettings getSettings() {
    return bgConsoleLoggerSettings.getInstance();
  }

  @Override
  protected PropertiesConsoleLoggerToolSetup createUi() {
    return new ConsoleLoggerConfigurableUI.ConsoleLoggerConfig(getSettings());
  }
}
