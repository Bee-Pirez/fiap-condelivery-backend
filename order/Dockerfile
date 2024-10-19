FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -f ./order/pom.xml

FROM openjdk:17
COPY --from=build ./order/target/order.jar ./order.jar
ENTRYPOINT ["java", "-jar", "order.jar"]