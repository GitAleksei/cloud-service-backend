FROM openjdk:11
EXPOSE 8081
ADD target/cloud-service-backend-0.0.1-SNAPSHOT.jar back.jar
ENTRYPOINT ["java", "-jar", "back.jar"]