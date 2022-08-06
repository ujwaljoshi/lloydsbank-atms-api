FROM openjdk:11
ADD build/libs/lloydsbank-atms-api-1.0.0.jar lloydsbank-atms-api-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lloydsbank-atms-api-1.0.0.jar"]