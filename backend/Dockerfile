FROM gradle:9.1.0-jdk21-corretto AS build
WORKDIR /backend
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY app ./app
RUN gradle build

FROM amazoncorretto:21-al2023-headless
WORKDIR /backend
COPY --from=build /backend/app/build/libs/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
