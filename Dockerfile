FROM eclipse-temurin:17-jdk-jammy

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-Dfile.encoding=UTF-8", "/app.jar"]