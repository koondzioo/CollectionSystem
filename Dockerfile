FROM ubuntu:latest
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install libxrender1 libxtst6 libxi6 -y
RUN apt-get install -y x11vnc xvfb firefox
FROM maven:3.6.1-jdk-11

FROM openjdk:12-jdk-oracle
ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin
EXPOSE 8080
WORKDIR /app
ADD target/interviewtask-0.0.1-SNAPSHOT.jar interviewtask-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.awt.headless=false", "-jar", "interviewtask-0.0.1-SNAPSHOT.jar"]