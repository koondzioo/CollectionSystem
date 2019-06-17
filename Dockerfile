FROM openjdk:11-jdk-oracle
EXPOSE 8086
WORKDIR /app
COPY chromedriver.exe /app
ADD target/interviewtask-0.0.1-SNAPSHOT.jar interviewtask-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "interviewtask-0.0.1-SNAPSHOT.jar"]