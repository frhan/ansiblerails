#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD target/jwtspring-0.0.1-SNAPSHOT.jar app.jar
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

# The smallest Docker image with OracleJDK 8 (167MB)
FROM frolvlad/alpine-oraclejdk8:slim

# add bash and coreutils
RUN apk add --no-cache bash coreutils

MAINTAINER altfatterz@gmail.com

# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories for
# Tomcat by default. The effect is to create a temporary file on your host under "/var/lib/docker" and link it to the
# container under "/tmp". This step is optional for the simple app that we wrote here, but can be necessary for other
# Spring Boot applications if they need to actually write in the filesystem.
VOLUME /tmp

# The project JAR file is ADDed to the container as "app.jar"
ADD jwtspring-0.0.1-SNAPSHOT.jar app.jar

# script files related to check if the datastore is “ready”
ADD wait-for-it.sh wait-for-it.sh
ADD start.sh start.sh

EXPOSE 8080

# You can use a RUN command to "touch" the jar file so that it has a file modification time
# (Docker creates all container files in an "unmodified" state by default)
# This actually isn’t important for the simple app that we wrote, but any static content (e.g. "index.html")
# would require the file to have a modification time.
RUN bash -c 'touch /app.jar'
RUN chmod +x ./start.sh
RUN chmod +x ./wait-for-it.sh
CMD ["./start.sh"]