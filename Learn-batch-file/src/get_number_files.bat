@echo off
set _count=1
for /f "tokens=*" %%G in ('dir /b E:\ManhPD5\*.pdf') do (
   echo %_count% : %%G
   set /a _count+=1
)
