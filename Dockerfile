# Use Java 21 since your project is built with 21
FROM openjdk:17-jdk-slim AS build

WORKDIR /app

# Copy Maven wrapper & config
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build application
RUN ./mvnw clean package -DskipTests

# ---- Runtime stage ----
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Run the jar
CMD ["java", "-jar", "app.jar"]
