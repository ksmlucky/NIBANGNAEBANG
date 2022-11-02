FROM openjdk:17-ea-11-jdk-slim as build-stage
WORKDIR /var/jenkins_home/workspace/deploy/backend/business-service
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build-stage /var/jenkins_home/workspace/deploy/backend/business-service/build/libs/business-service-0.0.1-SNAPSHOT.jar BusinessService.jar

ENTRYPOINT ["java","-jar","BusinessService.jar"]