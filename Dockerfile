# Builder
ARG OPENJDK_VERSION=25-jdk-slim
ARG MAVEN_VERSION=3.9.6-eclipse-temurin-17

FROM maven:${MAVEN_VERSION} AS builder
WORKDIR /app
COPY . .
RUN mvn --version
RUN mvn clean package -DskipTests

# Runner
FROM openjdk:${OPENJDK_VERSION}
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]