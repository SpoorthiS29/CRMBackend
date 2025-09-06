# Use official OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give permission to mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Package the app
RUN ./mvnw clean package -DskipTests

# Expose port (Render will map it)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/crmbackend-0.0.1-SNAPSHOT.jar"]

