FROM java:8
MAINTAINER yates
COPY target/tempgelerator-0.0.1-SNAPSHOT.jar /auto.jar
EXPOSE 8090
CMD java -jar /auto.jar