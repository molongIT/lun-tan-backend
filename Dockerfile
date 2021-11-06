FROM openjdk:8-jre
MAINTAINER pxlong
ADD ./target/MyTemplate-1.0-SNAPSHOT.jar MyTemplate-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "MyTemplate-1.0-SNAPSHOT.jar"]