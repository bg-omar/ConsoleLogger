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

    public static final String ACTIVE_PATTERN_1 = "console.log(\"1 ---> $$: \", $$);";
    public static final String ACTIVE_PATTERN_2 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String ACTIVE_PATTERN_3 = "console.log(\"%c 3 ---> $$: \",\"color:#ff0;\", $$);";
    public static final String ACTIVE_PATTERN_4 = "console.log(\"%c 4 ---> $$: \",\"color:#0F0;\", $$);";
    public static final String ACTIVE_PATTERN_5 = "console.warn(\"%c 5 ---> $$: \",\"color:#F00;\", $$);";
    public static final String ACTIVE_PATTERN_6 = "console.error(\"%c ({FP}:{LN}) $$: \", \"color:#F00;\", $$);";
    public static final String ACTIVE_PATTERN_7 = "Serial.println($$);";
    public static final String ACTIVE_PATTERN_8 = "print(\"{:>30}\".format(\" ---> $$: \" + $$));";
    public static final String ACTIVE_PATTERN_9 = "${'\n'}console.groupCollapsed(\"group $$\");${'\n'}console.groupEnd(\"end of group $$\");";

    public static final String DEFAULT_PATTERN_1 = "console.log(\"1 ---> $$: \", $$);";
    public static final String DEFAULT_PATTERN_2 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_3 = "console.log(\"%c 3 ---> $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_4 = "console.log(\"%c 4 ---> $$: \",\"color:#0F0;\", $$);";
    public static final String DEFAULT_PATTERN_5 = "console.warn(\"%c 5 ---> $$: \",\"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_6 = "console.error(\"%c ({FP}:{LN}) $$: \", \"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_7 = "Serial.println($$);";
    public static final String DEFAULT_PATTERN_8 = "print(\"{:>30}\".format(\" ---> $$: \" + $$));";
    public static final String DEFAULT_PATTERN_9 = "${'\n'}console.groupCollapsed(\"group $$\");${'\n'}console.groupEnd(\"end of group $$\");";

    public static final String DEFAULT_PATTERN_10 = "console.log(\"1 ---> $$: \", $$);";
    public static final String DEFAULT_PATTERN_11 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_12 = "console.log(\"%c 3 ---> $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_13 = "console.log(\"%c 4 ---> $$: \",\"color:#0F0;\", $$);";
    public static final String DEFAULT_PATTERN_14 = "console.warn(\"%c 5 ---> $$: \",\"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_15 = "console.error(\"%c ({FP}:{LN}) $$: \", \"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_16 = "Serial.println($$);";
    public static final String DEFAULT_PATTERN_17 = "print(\"{:>30}\".format(\" ---> $$: \" + $$));";
    public static final String DEFAULT_PATTERN_18 = "${'\n'}console.groupCollapsed(\"group $$\");${'\n'}console.groupEnd(\"end of group $$\");";


    private static final String[] patterns = {
            ACTIVE_PATTERN_1,
            ACTIVE_PATTERN_2,
            ACTIVE_PATTERN_3,
            ACTIVE_PATTERN_4,
            ACTIVE_PATTERN_5,
            ACTIVE_PATTERN_6,
            ACTIVE_PATTERN_7,
            ACTIVE_PATTERN_8,
            ACTIVE_PATTERN_9,
            DEFAULT_PATTERN_1,
            DEFAULT_PATTERN_2,
            DEFAULT_PATTERN_3,
            DEFAULT_PATTERN_4,
            DEFAULT_PATTERN_5,
            DEFAULT_PATTERN_6,
            DEFAULT_PATTERN_7,
            DEFAULT_PATTERN_8,
            DEFAULT_PATTERN_9,
            DEFAULT_PATTERN_10,
            DEFAULT_PATTERN_11,
            DEFAULT_PATTERN_12,
            DEFAULT_PATTERN_13,
            DEFAULT_PATTERN_14,
            DEFAULT_PATTERN_15,
            DEFAULT_PATTERN_16,
            DEFAULT_PATTERN_17,
            DEFAULT_PATTERN_18,
    };

    public String version = "0.0.24";

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
