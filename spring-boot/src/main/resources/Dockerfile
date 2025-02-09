FROM base-jdk:8
USER jboss
VOLUME /tmp
ADD springboot-1.0-SNAPSHOT.jar /tmp/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tmp/app.jar"]
