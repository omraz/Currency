@rem

@chcp 1250 > nul

@set PACKAGE=currency
@set CLASS=%1
@set JSON_PATH=c:\Mraz\Projects\java\lib\json-simple-1.1.jar

@if NOT EXIST %PACKAGE%\%CLASS%.class goto compile

@forfiles /P %PACKAGE% /M %CLASS%.class /C "cmd /c echo @ftime > ..\timestamp.tmp"
@set /P classTime=<timestamp.tmp
@forfiles /P %PACKAGE% /M %CLASS%.java /C "cmd /c echo @ftime > ..\timestamp.tmp"
@set /P javaTime=<timestamp.tmp

@if %javaTime% LEQ %classTime% goto runJava
:compile
@echo Compiling ...
javac -g -cp %JSON_PATH% %PACKAGE%/%CLASS%.java
@if ERRORLEVEL 1 goto fin

:runJava
@echo Running ...
java -cp %JSON_PATH%;. %PACKAGE%.%CLASS% %2 %3 %4 %5 %6 

:fin
@del timestamp.tmp