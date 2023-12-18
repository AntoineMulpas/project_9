FROM maven:3.8.4-openjdk-17-slim AS build

ARG IP_ADDRESS=localhost
ENV IP_ADDRESS=$IP_ADDRESS


WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .mvn/ .mvn
RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "/app/target/microservice-patient.jar"]
