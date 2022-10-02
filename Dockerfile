FROM openjdk:19
COPY target/techtest.jar /opt/artifact/techtest.jar
EXPOSE 8080 1044
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:1044"
CMD java -jar /opt/artifact/techtest.jar