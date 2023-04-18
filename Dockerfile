FROM maven:3.8.7-openjdk-18-slim AS builder
WORKDIR /app

COPY . ./

RUN mvn -Dmaven.test.skip clean package

FROM openjdk:17-alpine3.14

COPY --from=builder /app/target/restapi-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]