FROM openjdk:8-jre
MAINTAINER pxlong
RUN apt-get update
RUN apt-get install telnet
ADD /target/love-share-web-0.0.1.jar lun-tan-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lun-tan-backend.jar"]