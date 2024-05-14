package com.github.bgomar.bgconsolelogger.tools;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "ConsoleLoggerSettings", storages = {@Storage("consolelogger.xml")})
public class ConsoleLoggerSettings implements PersistentStateComponent<ConsoleLoggerSettings> {

    public static final String DEFAULT_PATTERN_1 = "console.log(\"1 ---> $$: \", $$);";
    public static final String DEFAULT_PATTERN_2 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_3 = "console.log(\"%c 3 ---> $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_4 = "console.log(\"%c 4 ---> $$: \",\"color:#0F0;\", $$);";
    public static final String DEFAULT_PATTERN_5 = "console.warn(\"%c 5 ---> $$: \",\"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_6 = "console.error(\"%c  $$: \", \"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_7 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_8 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_9 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";

    private static final String[] patterns = {
            DEFAULT_PATTERN_1,
            DEFAULT_PATTERN_2,
            DEFAULT_PATTERN_3,
            DEFAULT_PATTERN_4,
            DEFAULT_PATTERN_5,
            DEFAULT_PATTERN_6,
            DEFAULT_PATTERN_7,
            DEFAULT_PATTERN_8,
            DEFAULT_PATTERN_9
    };

    public String version = "0.0.25";

    public static ConsoleLoggerSettings getInstance() {
        return ApplicationManager.getApplication().getService(ConsoleLoggerSettings.class);
    }

    @Nullable
    @Override
    public ConsoleLoggerSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ConsoleLoggerSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static String getPattern(int index) {
        if (index >= 0 && index < patterns.length) {
            return patterns[index];
        } else {
            return patterns[index-1];
        }
    }

    public static void setPattern(int index, String pattern) {
        if (index >= 0 && index < patterns.length) {
            patterns[index] = pattern;
        }
    }
}
