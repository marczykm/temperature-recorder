FROM java:8-jre-alpine
ADD . /tmp
WORKDIR /tmp/target
EXPOSE 8080
CMD ["java", "-jar", "temperature-recorder-server-0.0.1-SNAPSHOT.jar"]