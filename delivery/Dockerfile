FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -f ./delivery/pom.xml

FROM openjdk:17
COPY --from=build ./delivery/target/delivery.jar ./delivery.jar
ENTRYPOINT ["java", "-jar", "delivery.jar"]