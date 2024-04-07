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

    public static final String DEFAULT_PATTERN_1 = "console.log(\"$$: \", $$);";
    public static final String DEFAULT_PATTERN_2 = "console.log(\"%c ---> $$: \",\"color:#f0f;\", $$);";
    public static final String DEFAULT_PATTERN_3 = "console.log(\"%c ---> $$: \",\"color:#ff0;\", $$);";
    public static final String DEFAULT_PATTERN_4 = "console.log(\"%c ---> $$: \",\"color:#0F0;\", $$);";
    public static final String DEFAULT_PATTERN_5 = "console.warn(\"%c ---> $$: \",\"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_6 = "console.error(\"%c ({FP}:{LN}) $$: \", \"color:#F00;\", $$);";
    public static final String DEFAULT_PATTERN_7 = "Serial.println($$);";
    public static final String DEFAULT_PATTERN_8 =  "print(\"{:>30}\".format(\" ---> $$: \" + $$));";
    public static final String DEFAULT_PATTERN_9 = "${'\n'}console.groupCollapsed(\"group $$\");${'\n'}console.groupEnd(\"end of group $$\");";


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

    public String version = "0.0.23";

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

    public static void resetPattern() {
        patterns[0] = DEFAULT_PATTERN_1;
        patterns[1] = DEFAULT_PATTERN_2;
        patterns[2] = DEFAULT_PATTERN_3;
        patterns[3] = DEFAULT_PATTERN_4;
        patterns[4] = DEFAULT_PATTERN_5;
        patterns[5] = DEFAULT_PATTERN_6;
        patterns[6] = DEFAULT_PATTERN_7;
        patterns[7] = DEFAULT_PATTERN_8;
        patterns[8] = DEFAULT_PATTERN_9;
    }
}
