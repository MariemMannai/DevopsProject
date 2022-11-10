FROM openjdk:8
ADD target/achat-1.0.jar  achatsirine.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "achatsirine.jar"]