FROM tomcat:8-jre8
MAINTAINER skostenko

RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/Familiehjelpen.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]