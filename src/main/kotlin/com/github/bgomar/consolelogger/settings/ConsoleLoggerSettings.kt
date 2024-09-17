package com.github.bgomar.consolelogger.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.Nullable

internal const val LOGG_CONSOLELOGGER_PATTERN  =   """console.log( -----> $$: ", $$);"""
internal const val DBUG_CONSOLELOGGER_PATTERN  = """console.debug( -----> $$: ", $$);"""
internal const val WARN_CONSOLELOGGER_PATTERN  =   """console.log("%c ---> $$: ","color:#F0F;", $$);"""
internal const val ERROR_CONSOLELOGGER_PATTERN =   """console.log("%c ---> $$: ","color:#0F0;", $$);"""
internal const val GROUP_CONSOLELOGGER_PATTERN =  """console.warn("%c ---> $$: ","color:#F00;", $$);"""
internal const val TABLE_CONSOLELOGGER_PATTERN =  """console.warn("%c ---> $$: ","color:#00F;", $$);"""


@Service
@State(name = "ConsoleLoggerSettings", storages = [(Storage("consolelogger.xml"))])
class ConsoleLoggerSettings : PersistentStateComponent<ConsoleLoggerSettings> {

  var logPattern: String = LOGG_CONSOLELOGGER_PATTERN
  var groupPattern: String = GROUP_CONSOLELOGGER_PATTERN
  var debugPattern: String = DBUG_CONSOLELOGGER_PATTERN
  var warnPattern: String = WARN_CONSOLELOGGER_PATTERN
  var errorPattern: String = ERROR_CONSOLELOGGER_PATTERN
  var tablePattern: String = TABLE_CONSOLELOGGER_PATTERN

  var version = "0.0.17"

  companion object {
    val instance: ConsoleLoggerSettings
      get() = ApplicationManager.getApplication().getService(ConsoleLoggerSettings::class.java)
  }

  @Nullable
  override fun getState(): ConsoleLoggerSettings = this

  override fun loadState(state: ConsoleLoggerSettings) {
    XmlSerializerUtil.copyBean(state, this)
  }
}
