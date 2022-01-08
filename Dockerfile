FROM java:8 
ENV MYSQL_HOST=mysql
EXPOSE 8082 3306
ADD target/timesheet-3.0.jar timesheet-3.0.jar
ENTRYPOINT ["java","-jar","/timesheet-3.0.jar"]
