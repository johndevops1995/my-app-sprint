# Usa una imagen base ligera de OpenJDK 21
FROM openjdk:21-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Copia el archivo .jar compilado al contenedor
COPY ./target/my-app-de-john-0.0.1-SNAPSHOT.jar .

# Variables de entorno configurables (Railway las define en su entorno)
ENV PROFILE_ENV=${PROFILE}
ENV SENTRY_ENVIRONMENT=${SENTRY_ENVIRONMENT}
ENV SENTRY_DSN=${SENTRY_DSN}

# Comando por defecto para ejecutar la app
CMD ["java", "-jar", "my-app-de-john-0.0.1-SNAPSHOT.jar"]
