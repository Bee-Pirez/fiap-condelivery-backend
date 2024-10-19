FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -f ./rating/pom.xml

FROM openjdk:17
COPY --from=build ./rating/target/rating.jar ./rating.jar
ENTRYPOINT ["java", "-jar", "rating.jar"]