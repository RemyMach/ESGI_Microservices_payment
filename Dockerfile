FROM maven:3-amazoncorretto-11 as builder

WORKDIR /app

COPY ./openapi/ ./openapi/
COPY ./project-micro/ ./project-micro/
COPY pom.xml ./pom.xml
RUN mvn clean install

FROM openjdk:11 as runner

WORKDIR /app

COPY --from=builder /app/project-micro/target/project-micro*.jar project-micro.jar

EXPOSE 8080

ENTRYPOINT ["java" ,"-jar", "-Dspring.profiles.active=env", "/app/project-micro.jar"]