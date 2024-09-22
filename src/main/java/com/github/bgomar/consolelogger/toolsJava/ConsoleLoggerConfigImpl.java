package com.github.bgomar.consolelogger.toolsJava;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ConsoleLoggerConfigImpl extends ConsoleLoggerConfigurableUI.ConsoleLoggerConfig {
    public ConsoleLoggerConfigImpl(@NotNull ConsoleLoggerSettings setting) {
        super(setting);
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return super.getPreferredFocusedComponent();
    }
}
