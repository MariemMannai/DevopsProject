FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/achat*.jar achat-1.0.jar 
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]