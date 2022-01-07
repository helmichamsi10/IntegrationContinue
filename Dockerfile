FROM openjdk:8-jdk-alpine
ENV MYSQL_HOST=mysql
EXPOSE 8083
ADD target/timesheet-3.0.jar timesheet-3.0.jar
ENTRYPOINT ["java","-jar","/timesheet-1.0.jar"]