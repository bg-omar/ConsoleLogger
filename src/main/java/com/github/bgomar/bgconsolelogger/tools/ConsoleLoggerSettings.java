package com.github.bgomar.bgconsolelogger.tools;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@State(name = "ConsoleLoggerSettings", storages = {@Storage("consolelogger.xml")})
public final class ConsoleLoggerSettings implements PersistentStateComponent<ConsoleLoggerSettings> {

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

    public static final String CHAPTER_PATTERN = "// CHAPTER:";

    private static final int DEFAULT_PATTERN_COUNT = 28;

    public List<String> patterns = new ArrayList<>(Arrays.asList(
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
            CHAPTER_PATTERN
    ));

    public String version = "0.0.32";

    public static ConsoleLoggerSettings getInstance() {
        ConsoleLoggerSettings settings = ApplicationManager.getApplication().getService(ConsoleLoggerSettings.class);
        if (settings.patterns.size() > DEFAULT_PATTERN_COUNT) {
            settings.patterns = settings.patterns.subList(0, DEFAULT_PATTERN_COUNT);
        }
        return settings;
    }

    @Override
    public ConsoleLoggerSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ConsoleLoggerSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    // Modify getPattern to retrieve from the List
    public static String getPattern(int index) {
        ConsoleLoggerSettings settings = getInstance();
        if (index >= 0 && index < settings.patterns.size()) {
            return settings.patterns.get(index);
        } else {
            return settings.patterns.get(settings.patterns.size() - 1);  // Default to last if out of bounds
        }
    }

    public static int getLogPatternsCount() {
        ConsoleLoggerSettings settings = getInstance();
        return settings.patterns.size() -1;  // Default to last if out of bounds

    }

    // Modify setPattern to update the List
    public static void setPattern(int index, String pattern) {
        ConsoleLoggerSettings settings = getInstance();
        if (index >= 0 && index < settings.patterns.size()) {
            settings.patterns.set(index, pattern);
        } else if (settings.patterns.size() < DEFAULT_PATTERN_COUNT) {
            settings.patterns.add(pattern);  // Add to the list if index is out of bounds and size is less than default
        } else {
            System.out.println("Cannot add more patterns. The list has reached its default limit.");
        }
    }
}