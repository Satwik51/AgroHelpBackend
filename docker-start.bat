@echo off
echo ====================================================
echo Starting AgroHelp Backend & PostgreSQL in Docker...
echo ====================================================

docker compose up --build -d

echo.
echo Waiting for services to become healthy...
timeout /t 5 /nobreak >nul

docker compose ps

echo.
echo ====================================================
echo Backend running on: http://localhost:8080/api/v1/
echo PostgreSQL running on: localhost:5432
echo To view logs, run: docker compose logs -f
echo ====================================================
pause
