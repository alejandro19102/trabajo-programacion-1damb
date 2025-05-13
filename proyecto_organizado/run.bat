@echo off
setlocal

REM == Ajusta según tu instalación de JavaFX ==
set "JAVAFX=C:\Users\guill\OneDrive\Escritorio\javafx-sdk-24\lib"

REM == Driver JDBC SQLite ==
set "JDBC=%~dp0lib\sqlite-jdbc.jar"

REM == Directorios de fuentes y salida ==
set "SRC_DIR=%~dp0src"
set "OUT_DIR=%~dp0out"

REM Crear carpetas de salida si no existen
if not exist "%OUT_DIR%"    mkdir "%OUT_DIR%"
if not exist "%OUT_DIR%\view"     mkdir "%OUT_DIR%\view"
if not exist "%OUT_DIR%\escenario" mkdir "%OUT_DIR%\escenario"

echo -------------------------------
echo Compilando archivos Java...
echo -------------------------------
javac --module-path "%JAVAFX%" --add-modules javafx.controls,javafx.fxml ^
    -classpath "%JDBC%" ^
    -d "%OUT_DIR%" ^
    "%SRC_DIR%\app\App.java" ^
    "%SRC_DIR%\controlador\*.java" ^
    "%SRC_DIR%\modelo\*.java"

if %errorlevel% neq 0 (
    echo.
    echo === ERROR DE COMPILACIÓN ===
    pause
    exit /b 1
)

echo.
echo -------------------------------
echo Copiando recursos FXML y escenario
echo -------------------------------
xcopy "%SRC_DIR%\view\*"      "%OUT_DIR%\view\"      /E /I /Y >nul
xcopy "%SRC_DIR%\escenario\*" "%OUT_DIR%\escenario\" /E /I /Y >nul

echo.
echo -------------------------------
echo Ejecutando la aplicación...
echo -------------------------------
java --module-path "%JAVAFX%" --add-modules javafx.controls,javafx.fxml ^
     -classpath "%JDBC%;%OUT_DIR%" app.App

pause



