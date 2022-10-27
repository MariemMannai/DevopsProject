FROM openjdk:8-jdk-alpine
Expose 8089
<<<<<<< HEAD
ADD target/chayma.jar chayma.jar
ENTRYPOINT ["java","-jar","/chayma.jar"]
=======
ADD target/achat-1.0.jar xyz.jar
ENTRYPOINT ["java","-jar","/xyz.jar"]
>>>>>>> mariem
