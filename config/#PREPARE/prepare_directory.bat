@echo off
echo.
echo ## Prepare directory for installation new schemas ##
echo.
echo Removing old directory if exists ...
if exist %1 rmdir %1 /s /q
echo Creating empty directory ...
mkdir %1
echo.
echo Recreation of directory %1 finished SUCCESSFULL
exit 1