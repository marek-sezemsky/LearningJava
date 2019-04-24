FROM maven:3-jdk-8

COPY src/ pom.xml ./

RUN  mvn install

CMD  mvn exec:java
