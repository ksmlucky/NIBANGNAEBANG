FROM openjdk:17-ea-11-jdk-slim as build-stage
WORKDIR /var/jenkins_home/workspace/deploy/backend/discovery-service
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build-stage /var/jenkins_home/workspace/deploy/backend/discovery-service/build/libs/discovery-service-0.0.1-SNAPSHOT.jar DiscoveryService.jar

EXPOSE 8761
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]

