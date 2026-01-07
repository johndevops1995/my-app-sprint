# FROM openjdk:21-slim
# RUN mvn clean install -DskipTests
# #ARG PROFILE
# #ARG SENTRY_DSN_ARG
# #ARG SENTRY_ENVIRONMENT_ARG

# #ENV PROFILE_ENV=$PROFILE
# #ENV SENTRY_ENVIRONMENT=$SENTRY_ENVIRONMENT_ARG
# #ENV SENTRY_DSN=$SENTRY_DSN_ARG

# COPY ./target/my-app-de-john-0.0.1-SNAPSHOT.jar /usr/src/app/
# WORKDIR /usr/src/app

# CMD ["java", "-jar", "my-app-de-john-0.0.1-SNAPSHOT.jar"]


# ---- Build Stage ----
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# ---- Runtime Stage ----
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/my-app-de-john-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV PROFILE_ENV=default

CMD ["java", "-Dspring.profiles.active=${PROFILE_ENV}", "-jar", "app.jar"]