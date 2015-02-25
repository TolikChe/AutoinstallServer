@echo off
echo.
echo ## Copy configuration to Subsystem destination ##
echo.
echo Removing old directory if exists ...
if exist %2 rmdir %2 /s /q
echo Creating empty directory ...
mkdir %2
xcopy %1 %2 /E
echo.
echo Configuration copied from %1 to %2 SUCCESSFULL
exit 1