FROM openjdk:17-alpine

WORKDIR /tmp

COPY target/team-quest-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]