
# ConsoleLogger

![Build](https://github.com/bg-omar/ConsoleLogger/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/22967.svg)](https://plugins.jetbrains.com/plugin/22967)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/22967.svg)](https://plugins.jetbrains.com/plugin/22967)

<h1 align="center">
    <a href="">
      <img src="https://raw.githubusercontent.com/bg-omar/ConsoleLogger/refs/heads/master/src/main/resources/META-INF/pluginIcon.svg?sanitize=true" width="84" height="84" alt="logo"/>
    </a><br/>
    ConsoleLogger
</h1>



An Intellij IDEA plugin.
Find useful tools in a Tool Window in the top right corner of your IDE:

**Work in progress**: 😎.
## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "ConsoleLogger"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/22967) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/22967/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/bg-omar/ConsoleLogger/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---

# Console Logger JetBrains Plugin

<!-- Plugin description -->
####  `CTRL + ALT + (1-9)` for editable console.logs
( 1 ) = ```console.log('$$: ', $$);```  (IntelliJ)  
( 2 ) = ```Serial.print('$$: '); Serial.println($$);``` (Arduino)  
( 3 ) = ```print('$$: ', $$);``` (Python)  
( 4 ) = ```System.out.println("$$: " + $$);``` (Java)  
( 5 ) = ```cout << "$$: " << $$ << endl;``` (C++)  
( 6 ) = ```out.println("$$: " + $$);``` (Kotlin)  

 `$$` = pre-selected variable / class / anything printable  
 `%c` = Console text color  
 `{LN}` = Line number         
 `{FN}` = File Name   

( \` ) = `Refresh all Line Numbers in file`  
( 0 ) = `remove all loggers (unedited)`      

## Toolwindow of ConsoleLogger
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/ConsoleLogger-0.0.29.png?raw=true" width="500px"/>]()
## Example of ConsoleLogger  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview1.png?raw=true" width="500px"/>]()  
## `Ctrl + Alt + 1`  on the `someReturn(theVariable);`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview2.png?raw=true" width="500px%"/>]()  
## Which will show in console
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview3.png?raw=true" width="500px%"/>]()  
## Remove all loggers  
### `(CTRL + ALT + 0)`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview6.png?raw=true" width="300px%"/>]()   

This is an educational project, still usefull for many developers.
This project is heavily based on LogIt by Laurent Soulet &  I Love DevToys by vshymanskyy

<!-- Plugin description end -->