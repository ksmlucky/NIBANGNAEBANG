FROM openjdk:17-ea-11-jdk-slim as build-stage
WORKDIR /var/jenkins_home/workspace/deploy/backend/config-service
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

WORKDIR /var/jenkins_home/workspace/deploy/backend/config-service/build/libs
#FROM openjdk:17-ea-11-jdk-slim
#COPY --from=build-stage /var/jenkins_home/workspace/deploy/backend/config-service/build/libs/config-service-0.0.1-SNAPSHOT.jar ConfigServer.jar

EXPOSE 8888
#ENTRYPOINT ["java","-jar","ConfigServer.jar"]
ENTRYPOINT ["java","-jar","config-service-0.0.1-SNAPSHOT.jar"]
