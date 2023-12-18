FROM maven:3.8.4-openjdk-17-slim AS build

ARG IP_ADDRESS=localhost
ENV IP_ADDRESS=$IP_ADDRESS

ARG AUTH_IP_ADDRESS=localhost
ENV AUTH_IP_ADDRESS=$AUTH_IP_ADDRESS

WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .mvn/ .mvn
RUN mvn clean package -DskipTests
EXPOSE 8765
CMD ["java", "-jar", "/app/target/microservice-api-gateway.jar"]
