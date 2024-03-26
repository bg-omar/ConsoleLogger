
<h1 align="center">
    <a href="">
      <img src="./src/main/resources/META-INF/pluginIcon.svg" width="84" height="84" alt="logo"/>
    </a><br/>
    ConsoleLogger
</h1>

An Intellij IDEA plugin.
Find useful tools in a Tool Window in the top right corner of your IDE:

**Work in progress**: 😎.
## Download
Download plugin from the JetBrains marketplace or via your IDE: <kbd>File</kbd>, <kbd>Settings...</kbd>, <kbd>Plugins</kbd>, 
<kbd>Marketplace</kbd> then select `ConsoleLogger`.

## ToDo
* improve the GUI
    * settings: remember last opened tool
* add more tools, like Base64 from file, GZip encoder/decoder, JWT encoder/decoder, text tools, graphics tools...



# ![Build](./src/main/resources/META-INF/pluginIcon.svg) Console Logger JetBrains Plugin

<!-- Plugin description -->
####  `CTRL + ALT + (1-9)` for console.logs [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/pluginIcon.svg?raw=true" width="16px"/>]() 
This is an educational joke project, still usefull for many developers.
Defaults:  May need personal adjustments in settings.  
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
### `Settings` -> `Tools` -> `ConsoleLogger`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/SettingPreview.png?raw=true" width="500px%"/>]()  
<!-- Plugin description end -->