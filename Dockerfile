FROM java:8 
EXPOSE 8083
ADD target target
ENTRYPOINT ["java","-jar","target/timesheet-3.0.jar"]
