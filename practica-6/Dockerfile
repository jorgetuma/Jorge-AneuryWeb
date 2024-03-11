# Etapa de compilación
FROM gradle:8.6.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

# Etapa de producción
FROM eclipse-temurin:21-jre-alpine
EXPOSE ${PORT:-8080}
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/practica6.jar
VOLUME /logs
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT:-8080}", "/app/practica6.jar"]