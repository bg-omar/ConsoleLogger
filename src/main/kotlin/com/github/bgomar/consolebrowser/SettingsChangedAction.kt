package com.github.bgomar.consolebrowser

import com.intellij.util.messages.Topic

interface SettingsChangedAction {

    fun settingsChanged()

    companion object {
        val TOPIC = Topic.create("Setting Change Topic", SettingsChangedAction::class.java)
    }
}
