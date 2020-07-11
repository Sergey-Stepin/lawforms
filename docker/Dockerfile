FROM java:8-jdk-alpine

#RUN mkdir /opt/lawforms/
#RUN mkdir /opt/lawforms/server/

COPY server-0.0.1-SNAPSHOT.jar /opt/

WORKDIR /opt

#ENV TZ Europe/Moscow

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server-0.0.1-SNAPSHOT.jar"]