**LLOYDSBANK-ATMS-API**

**Technical Details**-

This API is standard spring boot application.

The purpose of this api is to expose a GET endpoint to retrieve full details of Lloyds Bank ATMs by the Branch Identification key

GET endpoint-

A GET endpoint to retrieve full ATM details by Branch identification key

- GET /api/v1/branch/atms

**Technology stack-**
- java 11
- Spring Boot
- Junit
- Mockito
- JSON schema
- Slf4j for logging
- Actuator
- Swagger- springdoc-openapi
- Dockerfile
- Jacoco plugin for unit test report

**Development-**

Build - Download the code from GitHub master branch and open in an IDE ensuring that dependent libraries are loaded. In intelliJ tick the use of auto-import checkbox in the settings.

This API is a standard spring boot application. Confirm that the application builds and all the unit tests are pass by running the "./gradlew clean build" task. 

Unit test reports: You can view the test reports under folder - build/reports/tests/test/index.html

Run the application by using LloydsbankAtmsApiApplication class.

**Important links-**

Actuator health check endpoint - /actuator/health/custom (http://localhost:8080/actuator/health/custom)

Swagger endpoint - /swagger-ui-lloydsbank-atms-api.html (http://localhost:8080/swagger-ui-lloydsbank-atms-api.html)

**Docker**

Docker image name- ujwaljoshi/lloydsbank-atms-api-1.0.0 
(This image can be pull from docker hub - https://hub.docker.com/r/ujwaljoshi/lloydsbank-atms-api-1.0.0)

Instruction to run docker image:
1. Pull the docker image from docker hub -  
   docker pull ujwaljoshi/lloydsbank-atms-api-1.0.0
2. Check downloaded image -
   docker image ls or docker images 
3. Run the docker image -
   docker run -p 8080:8080 ujwaljoshi/lloydsbank-atms-api-1.0.0
4. Check if the container is running -
   docker ps
5. Check the spring boot application logs by using-
   docker logs <CONTAINER ID> -f

**Assumptions**

Listing down the assumptions made while developing the spring boot application -
1. I have used both endpoints from Lloyds Bank Open API https://api.lloydsbank.com/open-banking/v2.2/atms and https://api.lloydsbank.com/open-banking/v2.2/branches endpoint to retrieve atms and branches details.
2. As per the question I assumed identification key is the key present in branches response.
3. After looking at the Open API branches response I assumed the fields which can be unique are identification and Postcode
4. After looking at the Open API atms response the only field I could find was the postcode
5. Working of developed application-

    I have exposed GET endpoint - /api/v1/branch/atms with query parameters "url" and "identification"
    When we make a GET call to above end point, the application will call the Open API /branches end point and will fetch all the branches, 
    and it will filter only the branch with identification key which is passed as a query parameter, and it will extract PostCode.
    The application will then make a call to open API /atms endpoint, it will fetch all the atms and filter the response with extracted Postcode from /branches, and return the response with branch identification key and the associated list of ATMs with the branch.




