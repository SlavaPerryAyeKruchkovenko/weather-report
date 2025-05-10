FROM gradle:8.4.0-jdk17-alpine AS builder
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle
COPY src src

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-alpine

RUN addgroup -S weather-report && adduser -S spring -G weather-report
USER spring:spring

COPY --from=builder /app/build/libs/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]