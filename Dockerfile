FROM ubuntu:latest as build

RUN apt-get update && apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM debian:latest

RUN apt-get update && apt-get install -y openjdk-17-jdk mysql-server

EXPOSE 8080 3306

COPY --from=build /target/pessoaApi-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT service mysql start && java -jar app.jar
