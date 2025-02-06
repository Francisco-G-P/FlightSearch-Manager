# Official OpenJDK image as base
FROM openjdk:21-jdk-slim

# Establish project directory inside container
WORKDIR /app

# Copy Gradle files and configuration
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# Download Gradle dependencies
RUN chmod +x gradlew && ./gradlew dependencies

# Copy source code from backend to container
COPY src src

# Compile  project
RUN ./gradlew build --no-daemon

# Copy `.jar` created inside container
COPY build/libs/flight-search-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose backend port
EXPOSE 8080

# Command to run application
CMD ["java", "-jar", "app.jar"]

# To run backend image use: docker run -p 8080:8080 flight-search-backend
