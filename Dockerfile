FROM openjdk:8-jdk-alpine
Expose 8089
ADD http://192.168.1.24:8081/#browse/browse:maven-releases/tn/esprit/rh/Achat/1.0  xyz.jar
ENTRYPOINT ["java","-jar","/xyz.jar"]
