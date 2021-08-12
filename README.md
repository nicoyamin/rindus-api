# Rindus coding task

Rindus Challenge

## Technologies used

- Languages: 
   - [Java 14](https://www.java.com)
   - HTML, CSS and Typescript
- Frameworks and libraries: 
  - [Spring Framework, Spring Boot, Spring JPA, etc](https://spring.io/)
  - [Angular 12](https://angular.io/)
  - [Mockito](https://site.mockito.org/)
  - [Lombok](https://projectlombok.org/)
- Dependency management: [Maven](https://maven.apache.org/)
- API testing/documentation: [Swagger](https://swagger.io/)
- Unit Testing: [jUnit](http://junit.org/junit5/)


## Local Setup

### Pre-requisites
You must have the following installed in order to manage dependencies and build the project:
- For the backend: [Java 14](https://www.java.com/) and [Maven](https://maven.apache.org/)
- For the frontend: [Angular CLI](https://angular.io/cli) and [NPM](https://www.npmjs.com/)

### How to start the App
- Clone the [Repo](https://github.com/nicoyamin/rindus-api.git)
- Build the backend with Maven and start it locally. By default, application starts in http://localhost:8080/ 
- Once running, you can make API rest calls using a REST client such as [Postman](https://www.postman.com/) or navigating to  [Swagger UI](http://localhost:8080/swagger-ui.html#). In Swagger UI, you can find the documentation and contract definition for each resource and endpoint and you can try them out.
- Use NPM to download required UI dependencies. Then, you can use the command:
```
ng serve --open
``` 
- Angular Frontend start by default in http://localhost:4200/
- The frontend allows you to interact with the backend, which in turn calls the resources present in the following API: https://jsonplaceholder.typicode.com/

## Features

The application allows you to interact with the [JSON placeholder API](https://jsonplaceholder.typicode.com/). The following resources and HTTP methods have been implemented:

- Posts (GET, POST, PUT, PATCH, DELETE)
- Comments (GET, POST, PUT, PATCH, DELETE)
- Albums (GET, POST, PUT, PATCH, DELETE)
- Photos (GET, POST, PUT, PATCH, DELETE)
- ToDo's (GET, POST, PUT, PATCH, DELETE)
- Users (GET, POST, PUT, PATCH, DELETE)

Also, by making a GET call to any resource, you have the option to extract the response to a JSON or XML file (or both). These files are store in the following path **rindus-api/src/main/resources/extracted-resources/** (a couple of example files are already added). Extracting a resource to a file will overwrite the existing file for the same resource.

I have respected the JSON Placeholder API contract, but I have added validation in case a future upgrade requires implementing data persistence with a DB using a ORM such as Hibernate.

### Services

Once the backend is running, it is recommended to check each resource API contract in [SwaggerUI](http://localhost:8080/swagger-ui.html#)

Some REST calls are described below:

### Getting all albums and extracting to JSON:

**Curl**
```
curl -X GET "http://localhost:8080/albums?extractJson=true&extractXml=false" -H "accept: */*"
```
**Request URL**
```
http://localhost:8080/albums?extractJson=true&extractXml=false
```
**Response**
![get-response](https://user-images.githubusercontent.com/1058798/129123185-c3197919-96ae-4c45-b6b0-e74462fcb1fd.png)

### Posting a Comment:

**Curl**
```
curl -X POST "http://localhost:8080/comments" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"body\": \"New comment\", \"email\": \"testEmail@gmail.com\", \"id\": 1234, \"name\": \"Comment Name\", \"postId\": 5}"
```
**Request URL**
```
http://localhost:8080/comments
```
**Request body**
```
{
  "body": "New comment",
  "email": "testEmail@gmail.com",
  "id": 1234,
  "name": "Comment Name",
  "postId": 5
}
```
**Response**
![post-response](https://user-images.githubusercontent.com/1058798/129123467-2914c3ab-3508-4180-8a45-b47f51019f2b.png)

### Updating a Photo (using PUT):

**Curl**
```
curl -X PUT "http://localhost:8080/photos" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"albumId\": 1, \"id\": 1, \"thumbnailUrl\": \"https://via.placeholder.com/600/24f355\", \"title\": \"Updateed Title\", \"url\": \"https://via.placeholder.com/600/24f355\"}"
```
**Request URL**
```
http://localhost:8080/photos
```
**Request body**
```
{
  "albumId": 1,
  "id": 1,
  "thumbnailUrl": "https://via.placeholder.com/600/24f355",
  "title": "Updateed Title",
  "url": "https://via.placeholder.com/600/24f355"
}
```
**Response**
![put-response](https://user-images.githubusercontent.com/1058798/129123687-518ef540-49d9-428d-beb3-3f3a7b42afcb.png)

### Updating a Post (using PATCH):

**Curl**
```
curl -X PATCH "http://localhost:8080/posts" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 1, \"userId\": 1, \"title\": \"patched title\"}"
```
**Request URL**
```
http://localhost:8080/posts
```
**Request body**
```
{
  "id": 1,
  "userId": 1,
  "title": "patched title"
}
```
**Response**
![patch-response](https://user-images.githubusercontent.com/1058798/129123876-a6674823-f31a-4126-ae0c-f6851539ff9c.png)

### Deleting a ToDo:

**Curl**
```
curl -X DELETE "http://localhost:8080/todos?todoId=5" -H "accept: application/json"
```
**Request URL**
```
http://localhost:8080/todos?todoId=5
```
**Response**
![delete-response](https://user-images.githubusercontent.com/1058798/129124794-7fefd05b-0af5-493f-b3d2-48655cd1c040.png)


### Using the Angular frontend:
The Angular frontend offers a simple way to trigger the calls from the backend to the JSON placeholder API and display the results that the backend gets from it.

It consists of a couple of dropdown lists which let you select the HTTP Verb and resource and it will render the corresponding form for you to complete and submit, if necessary. After triggering the call, you will be able to see the response that the backend returns.

Some examples are below:

**Getting a list of resources:**
![angular-get](https://user-images.githubusercontent.com/1058798/129125203-0215f914-78de-432d-8574-c11b2d5511fa.png)

The response looks like this:
![angular-get-response](https://user-images.githubusercontent.com/1058798/129125261-a73e473b-6cfc-46e4-aa89-cf2d268787f4.png)

**Posting a resource:**
![angular-post](https://user-images.githubusercontent.com/1058798/129125343-4650ad55-43b6-4a22-ba07-2c7991b30853.png)

The response just relays the backend response as seen below:
![angular-post-response](https://user-images.githubusercontent.com/1058798/129125386-44d3788a-e1da-4d6c-a23a-b862395380b0.png)



## Unit testing

Junit 5 was used for automatic testing and Mockito for dependency mocking. A good number of test cases and branched are covered. Coverage report was generated using [JaCoCo](https://www.eclemma.org/jacoco/) and can be foun in the following folder:

[Coverage Report](https://github.com/nicoyamin/rindus-api/tree/master/src/test/coverage-report)

![coverage](https://user-images.githubusercontent.com/1058798/129125902-56096f88-a430-4094-8a69-ea61026faa31.PNG)


## Features to build upon/improve and things to add

Project can be improved in many aspects, which I'd be more than happy to discuss during the review. While considering improvements over EXISTING feature, I can mention the following:
- Increasing Unit test coverage and test cases for the  backend.
- Adding Unit testing for the frontend.
- Making the frontend display error codes returned by the backend. The error codes are being generated and returned correclty by the backend, but the frontend is currently not showing this error responses.
- API documentation is pretty extensive, but methods and classes should be documented as well with JavaDocs.
- Improve Frontend form validation and visual design.
- Making extracted JSON and/or XML files be downloaded to the client.
- Implementing the basic filtering and resource nesting oferred by the JSON placeholder API.


When it comes to NEW features that can be added to improve and polish the application, the below considerations can be made:

- Using a Database: In order the persist the data, better process it and to allow redundancy, a Database needs to be set up. Currently, the only "real" REST calls made to the API are the GETs. All other HTTP oprations are being simulated by the API, but not really persisted. That's why a POST request will not really persist the POSTed resource into the API backend. Implementing a DB on our Backend side will allow real persisting, updating and deletion of resources, as well as improving performance and offering a redundant store of data. Cosiderations need to be made with regards of using a relational or non relational DB: resources are simple enough to allow the use of NoSQL DBs, but if we are to build upon these, then a regular SQL would be better. Also, data consistency between the API and the backend needs to be considered (for instance, whether our backend will have immediate or eventual consistency) and the same could be said about caching-
- Object Relational Mapping: Following the above idea of using a SQL DB and allowing for data caching, we need to implement an ORM framework using a JPA implementation such as Hibernate to allow easier handling of our entities. Some validation and modeling is already in place so defining the relationship between resources and creating and implementing the corresponding Data repositories (to allow CRUD to our DB) should be pretty straightforward.
- Filtering, sorting and pagination: While JSON placeholder offers a simple filtering mechanism, leveraging the capabilites of having our own DB and ORM would allow us to build upon our service repositories to implement robust and flexible filtering, sorting and pagination of our queries and REST calls.
- CI/CD pipeline: A CI/CD pipeling implementation such as Git Labs or Jenkins is pending. 
- Monitoring: To add CM (continuos monitoring) to our CI/CD, we need a tool such as [Prometheus](https://prometheus.io/), which is an open source tool that acts as a scrapper for any metric that needs to be exposed from the application and also offers libraries to implement custom metrics in our code while allowing aggregation and querying of this information.
- Graphic Monitoring: We can use something like [Grafana](https://grafana.com/) to set the Prometheus service as Datasource to compile exposed metrics and present them graphically on a dashboard.
- Logging: There is no logging system set up, but it could be easily added using Log4j and later integrated with an elastic search tool such as [Loki](https://grafana.com/oss/loki/), which is compatible with Grafana.
- Containerization and Cloud deployment: This application could be easily containerized with Docker or Kubernetes and then deployed to a cloud provider such as AWS. This will allow for easy scalability and redundancy, plus the container tech is a perfect match for the monitoring bundle described above.
- API performance: Load tests using JMeter or simillar remain pending. It is possible to integrate performance metrics in a Grafana panel so they can be watched from there,

 **GENERAL SOFTWARE PRACTICES**
 
 - This being prototype, I decided to take some "shortcuts" in order to speed up development time.
 - I went for a single entity and requests for Model classes (resources), since the amount of information was manageable that
 way. Under normal circumstances, a response model needs to be added in order to fully isolate the entity from the request/response involved in a REST call.
 - For brevity's sake, I didn't code a repo interface for the services.
 - I used Lombok to create necessary getters, setters, hashcodes, etc.
 - I learned the Angular basics to be able to code the frontend. As such, the implementation might not follow best practices for this framework and there is no testing whatsoever.

Thanks for reading!
In case of questions or doubts, feel free to drop me an email at nicoyamin@hotmail.com.ar
