# Parental Control Service API
**
User Story Details:
**

**
Story Title - Prevent access to movies based on parental control level
**
***
As a customer I don’t want my account to be able to access movies that
have a higher parental control level than my current preference setting.
***
**
Task
**

The task for this assignment is to implement a parental control service API for a next generation video on demand platform.

## Installation
This project requires JDK 8. Install on linux using this command:


    sudo apt-get install openjdk-8-jdk


## Building the API
Gradle is used as the application build framework. Gradle comes with a wrapper called the Gradle Wrapper which allows the
application to be built without the need to install any third party software. To execute a gradle build, run the command:

    .\gradlew build

## Testing

The API Application depends on Junit for its unit testing.
All unit tests can be executed using the `test` gradle task:

    .\gradlew test

Using TestNG Data provider runner would reduce the number of unit tests. However, JUnit is used for easy understanding and readability.

**Spring Integration** is used to verify the end to end behavior of the API. However in this case, the integration test has been limited
to the web layer. This stops Spring from starting up a server to test the who application. In the real world, we may consider
enabling the integration test for the whole application.

## API Design
The `ParentalControlService` depends on a `MovieService` for the movies parental control levels (or "rating"). It by defaults uses a
 Parental Control Level `Comparator` to order parental control levels. This is what determine if the customer preferred
  rating is lower or higher than the movie rating. A `ParentalControlInfo` object returned as a response to the client.
  This is a **wrapper** class which is used to display a friendly message to the customer about been able to watch the movie. The ParentalControlInfo object
  takes in the customer preferred rating, movieId and a boolean response from the parental control service which is converted into a message to the customer.
  This is necessary because of the requirement for the Parental Control Service to return a boolean and makes it possible to fail safely.

### Spring Boot Framework
The Spring Boot Framework is used to simplify the bootstrapping and development of this application.
 The framework takes an opinionated approach to configuration, freeing developers from the need to define boilerplate configuration. It is one of the preferred framework used to building Restful API's.
 With Spring Boot, an embedded tomcat server is booted when the application is executed.

#### Spring Security
In the Real world, Spring Security Oauth2.0 will be used to secure the REST API. However for simplicity,
Spring default security is automatically configured with a default user and generated password.

In the browser prompt you will need to enter the credentials below:


    Username = "sky"
    Password = "sky".


It is explained in the Spring Boot Reference documentation in the Security section.

### Swagger
Documenting software/Rest API implementation is a very good common practice. Therefore Swagger is used in this implementation.
With Swagger, I am able to write useful documentation which will help other programmers to understand my REST API.
It also makes it easy for any additional rest end points to be documented when added. Of course there are other tools that could have been used such as:
DRF Docs, Apiary.

### Running the API Application
The Spring Boot Application can be started by executing the `run` gradle task:

    .\gradlew bootRun

This builds and run the application.

#### Accessing the REST API End Points Using Swagger.

    http://localhost:8080/swagger-ui.html

To access the end point, enter the credentials above, then hover the mouse on the parental control controller, and click
to list all API operations. Clicking the **expand operations** link to the right bottom of the page should expand all operations of the API endpoint.

#### Accessing the REST API End Point using a Rest Client.

    localhost:8080/api/parentalControls/rating/<preferredRating>?movieId=<movieId>

Example:

    localhost:8080/api/parentalControls/rating/15?movieId=Avengers

 **Available Movies** - Avengers, Mummy, Dunkirk, Spiderman, Kiddy, Failure

 **Movie Ratings** - U, PG, 12, 15, 18, None

