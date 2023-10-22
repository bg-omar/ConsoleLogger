# ![Build](./src/main/resources/META-INF/pluginIcon.svg) Console Logger JetBrains Plugin

<!-- Plugin description -->
#### `CTRL + ALT + (1-9)` for console.logs  
May need personal adjustments in settings.    
Defaults for CTRL + ALT + (1-9)  
(1) = `console.log("%c ---> $$: ","color:#F0F;", $$);`   
(2) = `console.debug("%c ---> $$: ","color:#0F0;", $$);`   
(3) = `console.warn("%c ---> $$: ","color:#F00;", $$);`   
(4) = `Serial.print(" ---> $$: ", $$);`   
(5) = `cout << "      ---> $$: " << $$ << endl;`    
(6) = `print("{:>30}".format(" ---> $$: " + $$))`  
(7) = `console.error("%c ({FP}:{LN}) $$: ", "color:#FF0;", $$);`  
(8) = `console.groupCollapsed("group $$");'\n' console.groupEnd("end of group $$");`  
(9) = `console.table($$);`  
(0) = `remove all loggers (unedited)`  

## Example of ConsoleLogger  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview1.png?raw=true" width="90%"/>]()  
#### `Ctrl + Alt + 1`  on the `someReturn(theVariable);`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview2.png?raw=true" width="90%"/>]()  
#### Which will show in console
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview3.png?raw=true" width="90%"/>]()  
## Remove all loggers  
#### `(CTRL + ALT + 0)`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/preview6.png?raw=true" width="30%"/>]()  
### `Settings` -> `Tools` -> `ConsoleLogger`  
#### [<img src="https://github.com/bg-omar/consolelogger/blob/master/.github/pics/SettingsPreview.png?raw=true" width="90%"/>]()  
<!-- Plugin description end -->
