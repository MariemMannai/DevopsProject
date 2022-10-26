FROM openjdk:8-jdk-alpine
Expose 8089
ADD target/chayma.jar chayma.jar
ENTRYPOINT ["java","-jar","/chayma.jar"]