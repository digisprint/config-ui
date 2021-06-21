FROM 1.8
EXPOSE 8080
ADD target/global-config.jar global-config.jar
ENTRYPOINT ["java","-jar","/global-config.jar"]