FROM openjdk:8-jdk-alpine
Expose 8089
ADD target/achat-1.0.jar xyz.jar
ENTRYPOINT ["java","-jar","/xyz.jar"]