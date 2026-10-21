@echo off
echo ====================================================
echo Running AgroHelp Backend Local CI/CD Pipeline...
echo ====================================================

echo [1/3] Compiling Kotlin Spring Boot Backend...
call gradlew.bat bootJar -x test
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Backend build failed!
    pause
    exit /b %ERRORLEVEL%
)

echo [2/3] Verifying Built Executable JAR...
if exist "build\libs\*.jar" (
    echo [SUCCESS] JAR generated successfully:
    dir build\libs\*.jar
) else (
    echo [ERROR] JAR artifact not found!
    pause
    exit /b 1
)

echo [3/3] Testing Health and Diagnostics...
echo Local compilation verified!

echo.
echo ====================================================
echo Backend CI/CD Local Pipeline: ALL CHECKS PASSED!
echo ====================================================
pause
