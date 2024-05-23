package com.github.bgomar.consolelogger.tools;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

@State(name = "ConsoleLoggerSettings", storages = {@Storage("consolelogger.xml")})
public class ConsoleLoggerSettings implements PersistentStateComponent<ConsoleLoggerSettings> {

    public static final String DEFAULT_PATTERN_1 = "console.log(\"%c 1 ---> $$: \",\"color:#fff;\", $$);";
    public static final String DEFAULT_PATTERN_2 = "console.log(\"%c 2 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_3 = "console.log(\"%c 3 ---> $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_4 = "console.log(\"%c 4 ---> $$: \",\"color:#0f0;\", $$);";
    public static final String DEFAULT_PATTERN_5 = "console.log(\"%c 5 ---> $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_6 = "console.log(\"%c 6 ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_7 = "console.log(\"%c 7 ---> $$: \",\"color:#00f;\", $$);";
    public static final String DEFAULT_PATTERN_8 = "console.warn(\"%c 8 ---> $$: \",\"color:#0ff;\", $$);";
    public static final String DEFAULT_PATTERN_9 = "console.error(\"%c 9 ---> $$: \",\"color:#000;\", $$);";

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
        }
    }

    public static int getLogPatternsCount() {
        return patterns.length;
    }
}