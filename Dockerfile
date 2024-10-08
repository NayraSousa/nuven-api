FROM maven:3.8.1-openjdk-17 AS maven

COPY /src /app/src

COPY /pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=maven /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]