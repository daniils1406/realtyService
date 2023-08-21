FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=Document-impl/build/libs/Document-impl-1.0-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]