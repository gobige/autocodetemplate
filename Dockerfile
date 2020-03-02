FROM java:8
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /tempgelerator.jar
ENTRYPOINT ["java", "-jar","/yates.jar"]