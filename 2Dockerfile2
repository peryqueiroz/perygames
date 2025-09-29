FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY build/libs/* build/lib/

COPY build/libs/perygames*.jar build/

WORKDIR /app/build

EXPOSE 8080

ENTRYPOINT java -jar perygames-0.0.1-SNAPSHOT.jar