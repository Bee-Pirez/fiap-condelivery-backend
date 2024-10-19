FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -f ./user/pom.xml

FROM openjdk:17
COPY --from=build ./user/target/user.jar ./user.jar
ENTRYPOINT ["java", "-jar", "user.jar"]