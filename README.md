### Getting started ###

### Project Description ###

The Rolling Revenue Report is one of the most important tools for Sr. Management to periodically analyze the performance of the Business Heads and compare against the revenue targets set in the beginning of the year. 

This report is currently prepared and shared with stakeholders by gathering inputs periodically from various individuals and collating and reviewing them and published at the end of fortnight cycle. The challenge lies in gathering the data from various business managers and collating them with accuracy. As this is done manually, value of automating this process is seen in improved productivity, reducing any inaccuracies and inefficiencies and standardizing the reports, in delivery and in format.


1. Change below properties as per Project

server.port=8080
server.servlet.context-path=/rollingrevenuereport
spring.application.name=RollingRevenueReport


2.Validation  

### OpenAPI  URL:

http://localhost:8080/rollingrevenuereport


3.
### To Build Maven 

To build run:

```maven clean build```

1.mvn install / mvn clean install
2.$ mvn spring-boot:run


### Database connection 

change the properties according to your environment

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/rolling_revenue_db
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=****
spring.datasource.password=*****

For Example:
(spring.profiles.active=dev)
If you are connected to MYSQL db to your Dev, At that time mention the environment in your application property file














