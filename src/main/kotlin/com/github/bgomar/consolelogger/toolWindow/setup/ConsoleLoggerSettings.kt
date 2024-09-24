package com.github.bgomar.consolelogger.toolWindow.setup

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "ConsoleLoggerSettings", storages = [Storage("consolelogger.xml")])
@Service(Service.Level.APP)
class ConsoleLoggerSettings : PersistentStateComponent<ConsoleLoggerSettings.State> {

    companion object {
        const val DEFAULT_VERSION = "0.0.29"

        const val DEFAULT_PATTERN_1 = """console.log("%c 1 --> {LN}||{FN}\n $$: ","color:#f0f;", $$);"""
        const val DEFAULT_PATTERN_2 = """console.log("%c 2 --> {LN}||{FN}\n $$: ","color:#0f0;", $$);"""
        const val DEFAULT_PATTERN_3 = """console.log("%c 3 --> {LN}||{FN}\n $$: ","color:#ff0;", $$);"""
        const val DEFAULT_PATTERN_4 = """console.log("%c 4 --> {LN}||{FN}\n $$: ","color:#f00;", $$);"""
        const val DEFAULT_PATTERN_5 = """console.log("%c 5 --> {LN}||{FN}\n $$: ","color:#0ff;", $$);"""
        const val DEFAULT_PATTERN_6 = """console.log("%c 6 --> {LN}||{FN}\n $$: ","color:#00f;", $$);"""
        const val DEFAULT_PATTERN_7 = """console.log("%c 7 --> {LN}||{FN}\n $$: ","color:#acf;", $$);"""
        const val DEFAULT_PATTERN_8 = """console.log("%c 8 --> {LN}||{FN}\n $$: ","color:#fca;", $$);"""
        const val DEFAULT_PATTERN_9 = """console.log("%c 9 --> {LN}||{FN}\n $$: ","color:#acf;", $$);"""

        const val DEFAULT_PATTERN_10 = """console.log("%c 10 --> {LN}||{FN}\n $$: ","color:#f0f;", $$);"""
        const val DEFAULT_PATTERN_11 = """console.log("%c 11 --> {LN}||{FN}\n $$: ","color:#0f0;", $$);"""
        const val DEFAULT_PATTERN_12 = """console.log("%c 12 --> {LN}||{FN}\n $$: ","color:#ff0;", $$);"""
        const val DEFAULT_PATTERN_13 = """console.log("%c 13 --> {LN}||{FN}\n $$: ","color:#f00;", $$);"""
        const val DEFAULT_PATTERN_14 = """console.log("%c 14 --> {LN}||{FN}\n $$: ","color:#0ff;", $$);"""
        const val DEFAULT_PATTERN_15 = """console.log("%c 15 --> {LN}||{FN}\n $$: ","color:#00f;", $$);"""
        const val DEFAULT_PATTERN_16 = """console.log("%c 16 --> {LN}||{FN}\n $$: ","color:#acf;", $$);"""
        const val DEFAULT_PATTERN_17 = """console.log("%c 17 --> {LN}||{FN}\n $$: ","color:#fca;", $$);"""
        const val DEFAULT_PATTERN_18 = """console.log("%c 18 --> {LN}||{FN}\n $$: ","color:#acf;", $$);"""

        const val ACTIVE_PATTERN_1 = """console.log("%c 1 --> {LN}||{FN}\n $$: ","color:#f0f;", $$);"""
        const val ACTIVE_PATTERN_2 = """console.log("%c 2 --> {LN}||{FN}\n $$: ","color:#0f0;", $$);"""
        const val ACTIVE_PATTERN_3 = """console.log("%c 3 --> {LN}||{FN}\n $$: ","color:#ff0;", $$);"""
        const val ACTIVE_PATTERN_4 = """console.log("%c 4 --> {LN}||{FN}\n $$: ","color:#f00;", $$);"""
        const val ACTIVE_PATTERN_5 = """console.log("%c 5 --> {LN}||{FN}\n $$: ","color:#0ff;", $$);"""
        const val ACTIVE_PATTERN_6 = """console.log("%c 6 --> {LN}||{FN}\n $$: ","color:#00f;", $$);"""
        const val ACTIVE_PATTERN_7 = """console.log("%c 7 --> {LN}||{FN}\n $$: ","color:#acf;", $$);"""
        const val ACTIVE_PATTERN_8 = """console.log("%c 8 --> {LN}||{FN}\n $$: ","color:#fca;", $$);"""
        const val ACTIVE_PATTERN_9 = """console.log("%c 9 --> {LN}||{FN}\n $$: ","color:#acf;", $$);"""

        // Default patterns
        val DEFAULT_PATTERNS = arrayOf(
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
            DEFAULT_PATTERN_18
        )

        fun getInstance(): ConsoleLoggerSettings {
            return ApplicationManager.getApplication().getService(ConsoleLoggerSettings::class.java)
        }

        fun getPattern(index: Int): String {
            val settings = getInstance().state
            return settings.patterns?.getOrNull(index) ?: DEFAULT_PATTERNS[index]
        }

        fun setPattern(index: Int, pattern: String) {
            val settings = getInstance().state
            if (index in (settings?.patterns?.indices ?: 0..8)) {
                settings?.patterns?.set(index, pattern)
            }
        }

        fun getLogPatternsCount(): Int {
            return DEFAULT_PATTERNS.size;
        }
    }

    // State class to hold the logger patterns and other settings
    class State {
        var patterns: Array<String> = DEFAULT_PATTERNS.copyOf()
        var version: String = DEFAULT_VERSION
    }

    private var state: State = State()

    override fun getState(): State {
        return state
    }

    override fun loadState(state: State) {
        XmlSerializerUtil.copyBean(state, this.state)
    }
}
