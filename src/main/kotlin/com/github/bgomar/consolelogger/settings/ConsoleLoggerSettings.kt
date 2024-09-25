package com.github.bgomar.consolelogger.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.ui.components.JBCheckBox
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.Nullable


internal const val DEFAULT_PATTERN_1 =  """console.log("%c ---> $$: ","color:#F0F;", $$);"""
internal const val DEFAULT_PATTERN_2 =  """console.log("%c ---> $$: ","color:#0F0;", $$);"""
internal const val DEFAULT_PATTERN_3 =  """console.warn("%c ---> $$: ","color:#F00;", $$);"""

internal const val DEFAULT_PATTERN_4 =  """Serial.print(" ---> $$: ", $$);"""
internal const val DEFAULT_PATTERN_5 =  """cout << "      ---> $$: " << $$ << endl;"""
internal const val DEFAULT_PATTERN_6 =  """print("{:>30}".format(" ---> $$: " + $$))"""

internal const val DEFAULT_PATTERN_7 =  """console.error("%c ({FP}:{LN}) $$: ", "color:#FF0;", $$);"""
internal const val DEFAULT_PATTERN_8 =  """${'\n'}console.groupCollapsed("group $$");""" +
                                        """${'\n'}console.groupEnd("end of group $$");"""
internal const val DEFAULT_PATTERN_9 =  """console.table($$);"""




@State(name = "ConsoleLoggerSettings", storages = [(Storage("consolelogger.xml"))])
class ConsoleLoggerSettings : PersistentStateComponent<ConsoleLoggerSettings> {


  var pattern1: String = DEFAULT_PATTERN_1
  var pattern2: String = DEFAULT_PATTERN_2
  var pattern3: String = DEFAULT_PATTERN_3
  var pattern4: String = DEFAULT_PATTERN_4
  var pattern5: String = DEFAULT_PATTERN_5
  var pattern6: String = DEFAULT_PATTERN_6
  var pattern7: String = DEFAULT_PATTERN_7
  var pattern8: String = DEFAULT_PATTERN_8
  var pattern9: String = DEFAULT_PATTERN_9
  var jbCheckBox: JBCheckBox =  JBCheckBox()

  var version = "0.0.21"
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
