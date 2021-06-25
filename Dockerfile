FROM tomcat:8.5-jdk8-openjdk
ARG WAR_FILE=target/*.war
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ${WAR_FILE} /usr/local/tomcat/webapps/config-app.war
CMD ["catalina.sh","run"]