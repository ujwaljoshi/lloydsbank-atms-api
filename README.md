LLOYDSBANK-ATMS-API

The purpose of this api is to expose a GET endpoint to retrieve full details of Lloyds Bank ATMs by the Branch Identification key

This API contains one GET endpoint-

A GET endpoint to retrieve full ATM details by Branch identification key

- GET /api/v1/branch/atms

Technology stack-
- java 11
- Spring Boot
- Junit
- Slf4j for logging
- Actuator
- Swagger- springdoc-openapi
- Dockerfile
- Jacoco plugin for unit test report

Important links-

Actuator health check endpoint - /actuator/health/custom

Swagger endpoint - /swagger-ui-lloydsbank-atms-api.html

Docker image name- ujwaljoshi/lloydsbank-atms-api-1.0.0


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




