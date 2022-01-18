FROM openjdk:11
WORKDIR / app
COPY ./target/*.jar ./aplication.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar

