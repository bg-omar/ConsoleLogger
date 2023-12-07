# ![Build](./src/main/resources/META-INF/pluginIcon.svg) Console Logger JetBrains Plugin

<!-- Plugin description -->
#### `CTRL + ALT + (1-9)` for console.logs  
May need personal adjustments in settings.    
This project is heavily based on LogIt by Laurent Soulet

Defaults for CTRL + ALT + (1-9)  
(1) = `console.log("$$: ", $$);"""` 
(2) = `console.log("%c ---> $$: ","color:#F0F;", $$);`   
(3) = `console.log("%c ---> $$: ","color:#0F0;", $$);`  
(4) = `console.log("%c ---> $$: ","color:#F00;", $$);`
(5) = `console.warn("%c ---> $$: ","color:#0FF;", $$);`
(6) = `console.debug("%c ---> $$: ","color:#FF0;", $$);`
(7) = `console.error("%c ({FP}:{LN}) $$: ", "color:#FF0;", $$);`  
(8) = `console.groupCollapsed("group $$");'\n' console.groupEnd("end of group $$");`  
(9) = `console.table($$);`  
(0) = `remove all loggers (unedited)`  

## Example of ConsoleLogger  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview1.png?raw=true" width="500px"/>]()  
#### `Ctrl + Alt + 1`  on the `someReturn(theVariable);`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview2.png?raw=true" width="500px%"/>]()  
#### Which will show in console
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview3.png?raw=true" width="500px%"/>]()  
## Remove all loggers  
#### `(CTRL + ALT + 0)`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview6.png?raw=true" width="300px%"/>]()  
### `Settings` -> `Tools` -> `ConsoleLogger`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/SettingPreview.png?raw=true" width="500px%"/>]()  
<!-- Plugin description end -->