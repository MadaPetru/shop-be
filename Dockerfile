# Use a base image with Java and Gradle
FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the necessary files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

COPY shop-api/build.gradle ./shop-api/build.gradle
COPY shop-api/src ./shop-api/src

COPY shop-app/build.gradle ./shop-app/build.gradle
COPY shop-app/src ./shop-app/src

COPY security-api/build.gradle ./security-api/build.gradle
COPY security-api/src ./security-api/src

COPY security-app/build.gradle ./security-app/build.gradle
COPY security-app/src ./security-app/src

COPY library-api/build.gradle ./library-api/build.gradle

COPY library-app/build.gradle ./library-app/build.gradle

COPY common/build.gradle ./common/build.gradle

# Make the gradlew script executable and remove Windows line endings
RUN chmod +x gradlew && sed -i 's/\r$//' gradlew

# Build the application using Gradle
RUN ./gradlew clean build -x test

EXPOSE 8080

# Specify the entry point command to run the application
CMD ["java", "-jar", "shop-app/build/libs/shop-app-1.0.0.jar"]
