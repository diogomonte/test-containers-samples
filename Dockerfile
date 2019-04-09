FROM openjdk:10-slim
MAINTAINER Diogo Monte


ADD ./build/libs/test-containers-samples-1.0-SNAPSHOT.jar app.jar

# Regarding settings of java.security.egd, see http://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]