#FROM eclipse-temurin:17-jdk-alpine
#RUN mkdir -p /app
#COPY .. /app
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=Authentification-impl/build/libs/Authentification-impl-1.0-SNAPSHOT.jar
ARG DB_HOST=5432
ARG DB_PORT=5432
ARG DB_USERNAME=postgres
ARG DB_PASSWORD=Landrover2013
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

