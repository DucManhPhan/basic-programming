@Echo off
::Setlocal
if {%1}=={} set _empty=Syntax: %0 "" &goto :message

::Does folder exist
if not exist %1 (
  set _empty=No Such Folder
  goto :message
)

:: Is folder empty
set _TMP=
for /f "delims=" %%a in ('dir /b %1') do set _TMP=%%a

IF {%_TMP%}=={} (
  set _empty=Empty
) ELSE (
  set _empty=Not Empty
)

:message
echo:
Echo %_empty%
::echo:
::Endlocal & set _empty=%_empty%