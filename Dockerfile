FROM openjdk:8-jdk-alpine
ENV MYSQL=mysql
EXPOSE 8082
ADD target/timesheet-3.0.jar timesheet-3.0.jar
ENTRYPOINT ["java","-jar","/timesheet-3.0.jar"]
