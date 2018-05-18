### Set the base image to Fedora
FROM jboss/base-jdk:8

### File Author / Maintainer
MAINTAINER "Venu " "svsvenu@gmail.com"

## Switch the user to root
USER root

### Install Webmethods

ADD target/camel-standalone-rest-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
ADD filebeat-6.2.4-linux-x86_64  /tmp
ADD run-application.sh /tmp
RUN chmod 777 /tmp/run-application.sh

### Open Ports, for this simple example we are only going to open up the admin port
EXPOSE 8090

ENTRYPOINT /tmp/run-application.sh
