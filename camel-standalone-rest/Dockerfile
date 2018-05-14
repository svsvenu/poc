### Set the base image to Fedora
FROM jboss/base-jdk:8

### File Author / Maintainer
MAINTAINER "Venu " "svsvenu@gmail.com"

## Switch the user to root
USER jboss

### Install Webmethods

ADD target/camel-standalone-rest-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
### Open Ports, for this simple example we are only going to open up the admin port
EXPOSE 8090

ENTRYPOINT java -jar /tmp/camel-standalone-rest-1.0-SNAPSHOT-jar-with-dependencies.jar
