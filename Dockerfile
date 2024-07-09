FROM amazoncorretto:17

WORKDIR /intuit-app

COPY target/intuit-0.0.1-SNAPSHOT.jar /intuit-app/intuit-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/intuit-app/intuit-0.0.1-SNAPSHOT.jar"]
