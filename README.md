
# ConsoleLogger

![Build](https://github.com/bg-omar/ConsoleLogger/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/22967.svg)](https://plugins.jetbrains.com/plugin/22967)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/22967.svg)](https://plugins.jetbrains.com/plugin/22967)

<h1 align="center">
    <a href="">
      <img src="./src/main/resources/META-INF/pluginIcon3.svg" width="84" height="84" alt="logo"/>
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

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/bg-omar/ConsoleLogger/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---

# ![Build](./src/main/resources/META-INF/pluginIcon.svg) Console Logger JetBrains Plugin

<!-- Plugin description -->
####  `CTRL + ALT + (1-9)` for console.logs
Defaults:  May need personal adjustments in toolwindow.  
( 1 ) = ```console.log('$$: ', $$);```  
( 2 ) = ```console.log('%c ---> $$: ','color:#0F0;', $$);```   

 `$$` = pre-selected variable / class / anything printable  
 `%c` = Console text color
           
( 0 ) = `remove all loggers (unedited)`      

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
