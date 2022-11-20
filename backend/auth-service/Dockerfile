FROM openjdk:17-ea-11-jdk-slim as build-stage
WORKDIR /var/jenkins_home/workspace/deploy/backend/auth-service
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

WORKDIR /var/jenkins_home/workspace/deploy/backend/auth-service/build/libs
#FROM openjdk:17-ea-11-jdk-slim
#COPY --from=build-stage /var/jenkins_home/workspace/deploy/backend/auth-service/build/libs/auth-service-0.0.1-SNAPSHOT.jar AuthService.jar

#ENTRYPOINT ["java","-jar","AuthService.jar"]
ENTRYPOINT ["java","-jar","auth-service-0.0.1-SNAPSHOT.jar"]