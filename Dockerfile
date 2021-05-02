FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/prefixanalyser-0.0.1-SNAPSHOT.jar prefixanalyser-0.0.1-SNAPSHOT.jar 
CMD [“java”,”-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/prefixanalyser-0.0.1-SNAPSHOT.jar ”]
