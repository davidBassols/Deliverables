# Deliverables
This repository contains a small project in Spring Boot that manages beers and is manufacturers. No configurations is required to make it start. 

It is configured to be executed on port 8080, which can be changed in configuration. 

I added swagger to have a UI of all methods and the required input, which can be found at: http://localhost:8080/swagger-ui/

I used H2 database to persist the data because it is a proof of concept and we do not require to persist the data in time, but could be changed to any other database.

I chosed PagingAndSortingRepository to make all endpoints available and decrease the amount of time of development, but gave me a hard time with testing, since I didn't 
find out how to create an instance of it. That's why there are no test. 

About the bonus steps:
- I added pagination to the getAll and search functionalities.
- Search functionality is available for Beers, allowing multiple criteria and to use any type of comarison.
