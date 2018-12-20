::@echo off
::for /f "usebackq tokens=1,2* delims=" %%G in ("E:\ManhPD5\Batch Programming\is_folder_empty.bat") do echo %%G

@echo off
set _ping_cmd=ping -n 5 172.217.161.163

FOR /f "tokens=4 delims=(=" %%G IN ('%_ping_cmd% ^|find "loss"') DO echo Result is [%%G]