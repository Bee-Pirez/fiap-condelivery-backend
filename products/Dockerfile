FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -f ./products/pom.xml

FROM openjdk:17
COPY --from=build ./products/target/products.jar ./products.jar
ENTRYPOINT ["java", "-jar", "products.jar"]