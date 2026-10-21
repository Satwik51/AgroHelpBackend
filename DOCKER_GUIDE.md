# 🐳 AgroHelp Docker Deployment Guide

Complete end-to-end guide to running **AgroHelp Kotlin Spring Boot Backend** & **PostgreSQL (2,080 Records)** using Docker and Docker Compose.

---

## 📁 Docker Files Structure

```
AgroHelpBackend/
├── Dockerfile                  # Multi-stage build for Kotlin Spring Boot (OpenJDK 21)
├── docker-compose.yml          # Orchestrates PostgreSQL + Spring Boot backend
├── .dockerignore               # Optimizes Docker build cache
├── docker-start.bat            # 1-Click Windows startup script
├── docker-stop.bat             # 1-Click Windows shutdown script
├── docker-logs.bat             # 1-Click container logs viewer
└── docker/
    └── init-db/
        ├── 01_schema.sql       # Creates tables (crops, fertilizers, mandi_prices, inquiries)
        └── 02_data.sql         # 2,080 verified records with mobile CDN images
```

---

## 🚀 Quick Start (In 3 Simple Steps)

### Step 1: Install Docker Desktop
If not already installed on your Windows machine:
1. Download from [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/).
2. Run the installer (ensure **WSL 2** option is checked).
3. Restart your PC if prompted and launch Docker Desktop.

### Step 2: Start Containers
Simply double-click `docker-start.bat` OR run:
```bash
cd c:\AndroidStudioProjects\AgroHelpBackend
docker compose up --build -d
```

Docker will:
1. Start `agrohelp_postgres` (PostgreSQL 16 Alpine).
2. Automatically run `01_schema.sql` and `02_data.sql` to populate all **2,080 records**.
3. Wait for PostgreSQL healthcheck (`pg_isready`).
4. Build and start `agrohelp_backend` (Kotlin Spring Boot 3.4.1).

### Step 3: Verify Everything is Live
Open your browser or terminal:
- **Backend API**: [http://localhost:8080/api/v1/crops](http://localhost:8080/api/v1/crops)
- **Database**: `localhost:5432` (Database: `agrohelp_db`, User: `postgres`, Password: `postgres`)

---

## 📱 Connecting Android App to Docker

When the backend runs in Docker:
- **On Android Emulator**: Connects to `http://10.0.2.2:8080/api/v1/`
- **On Physical Phone (Wi-Fi)**: Connects to `http://<YOUR_PC_IP>:8080/api/v1/` (e.g. `192.168.1.50:8080`)
- **On Physical Phone (USB)**: Run `adb reverse tcp:8080 tcp:8080` and connect to `http://localhost:8080/api/v1/`

You can also use the **Developer Diagnostics** screen in the AgroHelp Android app to tap `[⚡ Auto-Detect & Connect Best Host]`.

---

## 🛠️ Handy Docker Commands

| Action | Command |
|---|---|
| **Start in background** | `docker compose up -d` |
| **Rebuild & Start** | `docker compose up --build -d` |
| **View live logs** | `docker compose logs -f` |
| **View backend logs only** | `docker compose logs -f backend` |
| **Check container health** | `docker compose ps` |
| **Stop all containers** | `docker compose down` |
| **Reset database & re-seed** | `docker compose down -v && docker compose up -d` |
