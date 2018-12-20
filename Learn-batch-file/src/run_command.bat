::@echo off 

:: clear screen
cls  

set "path=..."

:: get command from user input
set /p _command=Input your command || set _command=NothingInput
if "%_command%"=="NothingInput" (
    echo Input do not have information 
    goto :exit
)

:: cd /d %path%

for /l %%G in (1, 1, 20) do (
    echo count=%%G
    cd /d "%path%"
    %_command%
)

pause

:exit
echo Exit command line