FROM openjdk:17-jdk
EXPOSE 8099
ARG JAR_FILE=target/springboot-bwl-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

