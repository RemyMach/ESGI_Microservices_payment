FROM maven:3-amazoncorretto-17 as builder

WORKDIR /app

COPY src ./src
COPY pom.xml ./pom.xml
RUN mvn clean install

FROM openjdk:17 as runner

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=env", "/app/app.jar"]