FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/chat-bot-v0.1.*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]