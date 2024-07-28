FROM openjdk:11-jre-slim
COPY target/marketing-application.jar /app/email-marketing.jar
ENTRYPOINT ["java", "-jar", "/app/email-marketing.jar"]
