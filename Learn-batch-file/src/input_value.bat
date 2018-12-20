@echo off

set /p _dept=Please enter Department || set _dept=NothingChosen

if "%_dept%"=="NothingChosen" goto :sub_error
if /i "%_dept%"=="finance" goto sub_finance
if /i "%_dept%"=="hr" goto sub_hr

goto:eof

:sub_finance
echo You chose the finance dept
goto:eof

:sub_hr
echo You chose the hr dept

:sub_error
echo Nothing was chosen

