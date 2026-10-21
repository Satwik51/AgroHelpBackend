# ==========================================
# Stage 1: Build the Application with Gradle
# ==========================================
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy gradle wrapper and build configs first for layer caching
COPY gradle/ gradle/
COPY gradlew build.gradle.kts settings.gradle.kts gradle.properties ./

# Fix permissions
RUN chmod +x gradlew

# Download dependencies
RUN ./gradlew dependencies --no-daemon || true

# Copy source code and build jar
COPY src/ src/
RUN ./gradlew bootJar --no-daemon -x test

# ==========================================
# Stage 2: Minimal Production JRE Runtime
# ==========================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Add a non-root dedicated user for security
RUN addgroup -S agrohelp && adduser -S agrohelp -G agrohelp

# Copy built artifact from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership
RUN chown -R agrohelp:agrohelp /app

USER agrohelp

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
