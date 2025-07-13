# ---- Stage 1: Build the JAR ----
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Set work directory
WORKDIR /app

# Copy all source code and pom.xml
COPY . .

# Build the JAR (skip tests to speed it up)
RUN ./mvnw clean package -DskipTests

# ---- Stage 2: Run the app ----
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]