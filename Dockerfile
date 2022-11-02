FROM openjdk:8-jdk-alpine
COPY target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0.jar"]
