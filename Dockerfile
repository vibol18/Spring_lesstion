# ============================================================
# Multi-stage Docker build for the real-school API
# Builds with Maven, runs on a slim Java 21 JRE. Linux/Style-safe
# (no Windows paths, no reliance on ./mvnw scripts).
# ============================================================

# ---- Stage 1: build ----
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Download dependencies first so layer caching works across builds.
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

# Skip tests in the image build; tests are run separately in CI/local.
RUN mvn clean package -DskipTests -B

# ---- Stage 2: runtime ----
FROM eclipse-temurin:21-jre

# Application runs with reduced privileges (uid 65534 = "nobody").
USER 65534:65534

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Render injects PORT; default to 10000 to match the Free plan.
ENV PORT=10000

EXPOSE 10000

# Memory-constrained defaults for Render Free (512 MB RAM).
# -XX:MaxRAMPercentage keeps the JVM heap inside the container memory limit.
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=70", "-jar", "app.jar"]