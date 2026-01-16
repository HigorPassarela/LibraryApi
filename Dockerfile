FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY target/libraryapi-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]