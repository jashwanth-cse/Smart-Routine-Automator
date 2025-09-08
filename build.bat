@echo off
echo === Cleaning old build ===
if exist out rmdir /s /q out
mkdir out

echo === Compiling SmartRoutineAutomator ===
javac -d out -cp out -sourcepath src\main\java src\main\java\com\smartroutine\Main.java

if %errorlevel% neq 0 (
    echo.
    echo !!! Compilation failed !!!
    pause
    exit /b %errorlevel%
)

echo === Running SmartRoutineAutomator ===
java -cp out com.smartroutine.Main

echo.
pause
