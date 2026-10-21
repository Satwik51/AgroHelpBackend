# 🚀 AgroHelp Complete CI/CD Automation Guide

This project includes fully automated **Continuous Integration & Continuous Deployment (CI/CD)** workflows powered by **GitHub Actions**.

---

## 🏗️ 1. Android App CI/CD Pipeline

**Workflow File**: `.github/workflows/android-ci-cd.yml`

### What it does automatically on every Git Push & Pull Request:
1. **Environment Setup**: Provisions clean Ubuntu runner with **Java 17 (Temurin)** and Android SDK.
2. **Smart Caching**: Caches Gradle dependencies and wrapper so subsequent builds complete in under 2 minutes.
3. **Automated Compilation**:
   - Compiles **Debug APK** (`app-debug.apk`).
   - Compiles **Release APK** (`app-release.apk`).
4. **Artifact Publishing**: Uploads the compiled APKs directly to GitHub Actions artifacts (downloadable for 30 days).
5. **Auto Release on Git Tag**: When you push a git tag (e.g. `git tag v1.0.0 && git push --tags`), it automatically creates a formal GitHub Release and attaches the APK!

### How to download the generated APK from GitHub:
1. Go to your GitHub repository.
2. Click on the **Actions** tab.
3. Click on the latest workflow run.
4. Scroll down to the **Artifacts** section and click **`agrohelp-debug-apk`** to download!

---

## 🐳 2. Backend & Docker CI/CD Pipeline

**Workflow File**: `AgroHelpBackend/.github/workflows/backend-ci-cd.yml`

### What it does automatically:
1. **Spring Boot Compilation**: Compiles Kotlin Spring Boot 3.4.1 using **Java 21 (Temurin)**.
2. **JAR Packaging**: Generates standalone executable `agrohelp-backend-1.0.0.jar`.
3. **Multi-Platform Docker Build**:
   - Uses `docker/setup-buildx-action` to build multi-arch container images (`linux/amd64`, `linux/arm64`).
   - Tags the image with branch name, git commit SHA, and `latest`.
4. **Registry Push**:
   - Automatically pushes to **GitHub Container Registry (`ghcr.io`)** using the default repository token.
   - (Optional) Pushes to **Docker Hub** if `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN` secrets are configured.
5. **Full-Stack Health Verification**: Spins up the PostgreSQL container + backend container in the CI environment, verifies healthcheck and database connectivity, then tears it down cleanly.

---

## 💻 3. Local CI/CD Test Scripts (1-Click Run)

You can run the exact same CI/CD checks locally on your Windows PC before pushing to GitHub:

- **For Android App**: Double-click `AgroHelp/ci-local-test.bat`
- **For Backend**: Double-click `AgroHelpBackend/ci-local-test.bat`

---

## 🔑 GitHub Secrets (Optional Configuration)

| Secret Name | Description | Required? |
|---|---|---|
| `GITHUB_TOKEN` | Automatically provided by GitHub | ✅ Default |
| `DOCKERHUB_USERNAME` | Your Docker Hub username | Optional (for Docker Hub push) |
| `DOCKERHUB_TOKEN` | Your Docker Hub personal access token | Optional (for Docker Hub push) |
