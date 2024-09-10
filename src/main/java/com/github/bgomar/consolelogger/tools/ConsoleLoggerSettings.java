package com.github.bgomar.consolelogger.tools;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.diagnostic.Logger;

import java.io.*;


@State(name = "ConsoleLoggerSettings", storages = {@Storage("consolelogger.xml")})
public class ConsoleLoggerSettings implements PersistentStateComponent<ConsoleLoggerSettings.State> {

    public static final String DEFAULT_PATTERN_1 = "console.log(\"%c 1 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_2 = "console.log(\"%c 2 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);";
    public static final String DEFAULT_PATTERN_3 = "console.log(\"%c 3 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_4 = "console.log(\"%c 4 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);";
    public static final String DEFAULT_PATTERN_5 = "console.log(\"%c 5 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_6 = "console.log(\"%c 6 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);";
    public static final String DEFAULT_PATTERN_7 = "console.log(\"%c 7 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String DEFAULT_PATTERN_8 = "console.log(\"%c 8 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);";
    public static final String DEFAULT_PATTERN_9 = "console.log(\"%c 9 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";

    public static final String DEFAULT_PATTERN_10 = "console.log(\"%c 1 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_11 = "console.log(\"%c 2 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);";
    public static final String DEFAULT_PATTERN_12 = "console.log(\"%c 3 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_13 = "console.log(\"%c 4 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);";
    public static final String DEFAULT_PATTERN_14 = "console.log(\"%c 5 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_15 = "console.log(\"%c 6 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);";
    public static final String DEFAULT_PATTERN_16 = "console.log(\"%c 7 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String DEFAULT_PATTERN_17 = "console.log(\"%c 8 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);";
    public static final String DEFAULT_PATTERN_18 = "console.log(\"%c 9 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String DEFAULT_PATTERN_19 = "console.log(\"%c 1 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_20 = "console.log(\"%c 2 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);";
    public static final String DEFAULT_PATTERN_21 = "console.log(\"%c 3 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_22 = "console.log(\"%c 4 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);";
    public static final String DEFAULT_PATTERN_23 = "console.log(\"%c 5 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_24 = "console.log(\"%c 6 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);";
    public static final String DEFAULT_PATTERN_25 = "console.log(\"%c 7 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String DEFAULT_PATTERN_26 = "console.log(\"%c 8 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);";
    public static final String DEFAULT_PATTERN_27 = "console.log(\"%c 9 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";

    private static String[] patterns = {
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
            DEFAULT_PATTERN_19,
            DEFAULT_PATTERN_20,
            DEFAULT_PATTERN_21,
            DEFAULT_PATTERN_22,
            DEFAULT_PATTERN_23,
            DEFAULT_PATTERN_24,
            DEFAULT_PATTERN_25,
            DEFAULT_PATTERN_26,
            DEFAULT_PATTERN_27,
    };

    public String version = "0.0.29";

    public static ConsoleLoggerSettings getInstance() {

        return ApplicationManager.getApplication().getService(ConsoleLoggerSettings.class);
    }

    public static class State {
        static String[] patterns = {
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
                DEFAULT_PATTERN_19,
                DEFAULT_PATTERN_20,
                DEFAULT_PATTERN_21,
                DEFAULT_PATTERN_22,
                DEFAULT_PATTERN_23,
                DEFAULT_PATTERN_24,
                DEFAULT_PATTERN_25,
                DEFAULT_PATTERN_26,
                DEFAULT_PATTERN_27,
        };
    }


    @Override
    public State getState() {
        State state = new State();
        State.patterns = patterns;
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        patterns = State.patterns;
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
        } else {
            patterns[index-1] = pattern;
        }
    }

    public static int getLogPatternsCount() {
        return patterns.length;
    }
}
