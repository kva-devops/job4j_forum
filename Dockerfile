FROM tomcat:9-jdk14-openjdk
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/forum-1.war /usr/local/tomcat/webapps/ROOT.war
RUN sh -c 'touch /usr/local/tomcat/webapps/ROOT.war'
CMD ["catalina.sh", "run"]