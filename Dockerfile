# Use official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built jar (make sure to build it first!)
COPY target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]