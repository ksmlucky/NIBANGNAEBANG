FROM openjdk:17-ea-11-jdk-slim as build-stage
WORKDIR /var/jenkins_home/workspace/deploy/backend/apigateway-service
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

WORKDIR /var/jenkins_home/workspace/deploy/backend/apigateway-service/build/libs
#FROM openjdk:17-ea-11-jdk-slim
#COPY --from=build-stage /var/jenkins_home/workspace/deploy/backend/apigateway-service/build/libs/apigateway-service-0.0.1-SNAPSHOT.jar ApigatewayService.jar

EXPOSE 8080
#ENTRYPOINT ["java","-jar","ApigatewayService.jar"]
ENTRYPOINT ["java","-jar","apigateway-service-0.0.1-SNAPSHOT.jar"]