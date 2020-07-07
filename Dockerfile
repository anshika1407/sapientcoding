FROM openjdk:latest
EXPOSE 8000
ADD /target/sapient-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
