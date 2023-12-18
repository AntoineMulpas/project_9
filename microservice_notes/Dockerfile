FROM maven:3.8.4-openjdk-17-slim AS build

ARG IP_ADDRESS=localhost
ENV IP_ADDRESS=$IP_ADDRESS

ARG EUREKA_IP_ADDRESS=localhost
ENV EUREKA_IP_ADDRESS=$EUREKA_IP_ADDRESS

ARG MONGO_IP=localhost
ENV MONGO_IP=$MONGO_IP

WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .mvn/ .mvn
RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests
EXPOSE 8081
CMD ["java", "-jar", "/app/target/microservice-note.jar"]
