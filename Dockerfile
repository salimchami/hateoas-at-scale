FROM openjdk:21-jdk-slim AS base
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

FROM base AS build
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar

FROM build AS run
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]