FROM openjdk:17
COPY target/desafio-0.0.1-SNAPSHOT.jar desafio-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/desafio-0.0.1-SNAPSHOT.jar"]