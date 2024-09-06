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

    private static String[] patterns = {
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

    public String version = "0.0.28";

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
                DEFAULT_PATTERN_9
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

    public static void saveSettings() {
        try (FileWriter writer = new FileWriter("consolelogger.xml")) {
            for (String pattern : patterns) {
                writer.write(pattern + "\n");
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
}
