# Etapa de compilación
FROM gradle:8.6.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

# Etapa de producción
FROM eclipse-temurin:21-jre-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/practica4.jar
VOLUME /logs
ENTRYPOINT ["java", "-jar", "/app/practica4.jar"]