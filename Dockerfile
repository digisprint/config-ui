FROM tomcat:jdk8-openjdk
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} /usr/local/tomcat/webapps/global-config.war
CMD ["catalina.sh","run"]