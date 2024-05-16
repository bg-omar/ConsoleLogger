package com.github.bgomar.consolelogger.settings

import com.intellij.openapi.options.ConfigurableUi
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import javax.swing.JComponent


class ConsoleLoggerConfigurableUI(setting: ConsoleLoggerSettings) : ConfigurableUi<ConsoleLoggerSettings> {
  private val ui: DialogPanel = panel {
    var textField1: Cell<JBTextField>
    row("(CTRL + ALT + 1) ") {
      textField1 = textField()
        .bindText(setting::pattern1)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
          component.toolTipText = "You can put here whatever you want, $$ is the selected variable, {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField1.component.text = DEFAULT_PATTERN_1
      }).apply {
        component.toolTipText = "Reset to default pattern"
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField2: Cell<JBTextField>
    row("(CTRL + ALT + 2) ") {
      textField2 = textField()
        .bindText(setting::pattern2)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
         component.toolTipText = "You can put here whatever you want, $$ is the selected variable, {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField2.component.text = DEFAULT_PATTERN_2
      }).apply {
        component.toolTipText = "Reset to default pattern"
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField3: Cell<JBTextField>
    row("(CTRL + ALT + 3) ") {
      textField3 = textField()
        .bindText(setting::pattern3)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
         component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number " 
        }

      button("Default", actionListener = {
        textField3.component.text = DEFAULT_PATTERN_3
      }).apply {
        component.toolTipText = "Reset to default pattern"
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField4: Cell<JBTextField>
    row("(CTRL + ALT + 4) ") {
      textField4 = textField()
        .bindText(setting::pattern4)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
         component.toolTipText = "Serial.print(\" ---> \$\$: \", \$\$);, where \$\$ = selected,,  {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField4.component.text = DEFAULT_PATTERN_4
      }).apply {
        component.toolTipText = "Reset to default pattern"
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField5: Cell<JBTextField>
    row("(CTRL + ALT + 5) ") {
      textField5 = textField()
        .bindText(setting::pattern5)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
         component.toolTipText = "cout << \"      ---> \$\$: \" << \$\$ << endl;, where \$\$ = selected,  {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField5.component.text = DEFAULT_PATTERN_5
      }).apply {
        component.toolTipText = "Reset to default pattern"
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField6: Cell<JBTextField>
    row("(CTRL + ALT + 6) ") {
      textField6 = textField()
        .bindText(setting::pattern6)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
          component.toolTipText = "print(\"{:>30}\".format(\" ---> \$\$: \" + \$\$)), where \$\$ = selected, {:>30} = start string from 30,  {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField6.component.text = DEFAULT_PATTERN_6
      }).apply {
        component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField7: Cell<JBTextField>
    row("(CTRL + ALT + 7) ") {
      textField7 = textField()
        .bindText(setting::pattern7)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
          component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField7.component.text = DEFAULT_PATTERN_7
      }).apply {
        component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField8: Cell<JBTextField>
    row("(CTRL + ALT + 8) ") {
      textField8 = textField()
        .bindText(setting::pattern8)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
          component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField8.component.text = DEFAULT_PATTERN_8
      }).apply {
        component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
      }
    }.layout(RowLayout.PARENT_GRID)

    var textField9: Cell<JBTextField>
    row("(CTRL + ALT + 9) ") {
      textField9 = textField()
        .bindText(setting::pattern9)
        .align(AlignX.FILL)
        .gap(RightGap.SMALL)
        .resizableColumn().apply {
          component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
        }

      button("Default", actionListener = {
        textField9.component.text = DEFAULT_PATTERN_9
      }).apply {
        component.toolTipText = "console.log(\"%c\$\$: \",\"color:#F0F;\", \$\$), where \$\$ = selected, %c = color:#......;,  {FP} filepath, {FN} filename, {LN} line number "
      }
    }.layout(RowLayout.PARENT_GRID)
  }

  override fun reset(settings: ConsoleLoggerSettings) {
    ui.reset()
  }

  override fun isModified(settings: ConsoleLoggerSettings): Boolean {
    return ui.isModified()
  }

  override fun apply(settings: ConsoleLoggerSettings) {
    ui.apply()
  }

  override fun getComponent(): JComponent {
    return ui
  }
}