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

        // Default patterns
        val DEFAULT_PATTERNS = arrayOf(
            "console.log(\"%c 1 --> {LN}||{FN}\\n $$: \",\"color:#f0f;\", $$);",
            "console.log(\"%c 2 --> {LN}||{FN}\\n $$: \",\"color:#0f0;\", $$);",
            "console.log(\"%c 3 --> {LN}||{FN}\\n $$: \",\"color:#ff0;\", $$);",
            "console.log(\"%c 4 --> {LN}||{FN}\\n $$: \",\"color:#f00;\", $$);",
            "console.log(\"%c 5 --> {LN}||{FN}\\n $$: \",\"color:#0ff;\", $$);",
            "console.log(\"%c 6 --> {LN}||{FN}\\n $$: \",\"color:#00f;\", $$);",
            "console.log(\"%c 7 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);",
            "console.log(\"%c 8 --> {LN}||{FN}\\n $$: \",\"color:#fca;\", $$);",
            "console.log(\"%c 9 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);"
        )

        fun getInstance(): ConsoleLoggerSettings {
            return ApplicationManager.getApplication().getService(ConsoleLoggerSettings::class.java)
        }

        fun getPattern(index: Int): String {
            val settings = getInstance().state
            return settings?.patterns?.getOrNull(index) ?: DEFAULT_PATTERNS[index]
        }

        fun setPattern(index: Int, pattern: String) {
            val settings = getInstance().state
            if (index in (settings?.patterns?.indices ?: 0..8)) {
                settings?.patterns?.set(index, pattern)
            }
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
