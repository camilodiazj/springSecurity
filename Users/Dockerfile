FROM openjdk:8
VOLUME /tmp
EXPOSE 8085
ADD ./target/Users-0.0.1-SNAPSHOT.jar users-server.jar
ENTRYPOINT ["java","-jar","/users-server.jar"]