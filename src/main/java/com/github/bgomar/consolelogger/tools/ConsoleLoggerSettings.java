package com.github.bgomar.consolelogger.tools;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;


@Service
@State(name = "ConsoleLoggerSettings", storages = {@Storage("consolelogger.xml")})
public final class ConsoleLoggerSettings implements PersistentStateComponent<ConsoleLoggerSettings.State> {

    public static final String DEFAULT_PATTERN_1 = "console.log(\"%c 1 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_2 = "console.log(\"%c 2 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);";
    public static final String DEFAULT_PATTERN_3 = "console.log(\"%c 3 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_4 = "console.log(\"%c 4 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);";
    public static final String DEFAULT_PATTERN_5 = "console.log(\"%c 5 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_6 = "console.log(\"%c 6 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);";
    public static final String DEFAULT_PATTERN_7 = "console.log(\"%c 7 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String DEFAULT_PATTERN_8 = "console.log(\"%c 8 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);";
    public static final String DEFAULT_PATTERN_9 = "console.log(\"%c 9 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";

    public static final String DEFAULT_PATTERN_10 = "console.log(\"%c 10 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_11 = "console.log(\"%c 11 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);";
    public static final String DEFAULT_PATTERN_12 = "console.log(\"%c 12 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_13 = "console.log(\"%c 13 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);";
    public static final String DEFAULT_PATTERN_14 = "console.log(\"%c 14 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_15 = "console.log(\"%c 15 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);";
    public static final String DEFAULT_PATTERN_16 = "console.log(\"%c 16 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String DEFAULT_PATTERN_17 = "console.log(\"%c 17 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);";
    public static final String DEFAULT_PATTERN_18 = "console.log(\"%c 18 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";

    public static final String ACTIVE_PATTERN_1 = "console.log(\"%c 1 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);";
    public static final String ACTIVE_PATTERN_2 = "console.log(\"%c 2 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);";
    public static final String ACTIVE_PATTERN_3 = "console.log(\"%c 3 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);";
    public static final String ACTIVE_PATTERN_4 = "console.log(\"%c 4 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);";
    public static final String ACTIVE_PATTERN_5 = "console.log(\"%c 5 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);";
    public static final String ACTIVE_PATTERN_6 = "console.log(\"%c 6 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);";
    public static final String ACTIVE_PATTERN_7 = "console.log(\"%c 7 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
    public static final String ACTIVE_PATTERN_8 = "console.log(\"%c 8 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);";
    public static final String ACTIVE_PATTERN_9 = "console.log(\"%c 9 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";

    private static String[] patterns = {
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

    public String version = "0.0.29";

    public static ConsoleLoggerSettings getInstance() {
        return ApplicationManager.getApplication().getService(ConsoleLoggerSettings.class);
    }

    public static class State {
        static String[] patterns = {
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
    }




    @Override
    public @Nullable State getState() {
        State state = new State();
        state.patterns = patterns;
        return state;

    }

    @Override
    public void loadState(@NotNull State state) {
        patterns = state.patterns;
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public void noStateLoaded() {
        PersistentStateComponent.super.noStateLoaded();
    }

    @Override
    public void initializeComponent() {
        PersistentStateComponent.super.initializeComponent();
    }


    public static void saveSettings() {
        try (FileWriter writer = new FileWriter("consolelogger.xml")) {
            for (int i = 0; i < patterns.length; i++) {
                writer.write(patterns[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSettings() {
        try (BufferedReader reader = new BufferedReader(new FileReader("consolelogger.xml"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < patterns.length) {
                patterns[index] = line;
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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