# KPN Development Assignment
## How to run the application

This solution is implemented using Java 14, Spring Boot, Spring Batch and MongoDB. 

* Run the Spring Boot application with Maven: `mvn spring-boot:run`
* On start up, the application imports the data from the `movies.xml` and `profiles.json` files using Spring Batch
* To query the application for movie suggestions, query the REST endpoint using curl or a tool like Postman: `localhost:8080/api/kpnmovies/v1/movie/suggestion/customer/id/{id}` 

## Assignment description
### Input

Given a movies.xml file with available movies and a profiles.json file with customer interests.

### Application

Create a Java application that can process both input files and store the data in a local database. 
Your application can handle the following request:

`'/api/yourapp/v1/movie/suggestion/customer/id/{id}'`

Based on the id retrieve the customer interests and return a list of suggested movies in json format.

### Example output

```json
[
	{
		"title": "abc",
		"imdb": "http://example.org/abc"
	},
	{
		"title": "foo",
		"imdb": "http://example.org/foo"
	}
]
```

### Application requirements

* Uses Spring Boot;
* Uses a yaml file for configuration;
* Uses a in-memory database;
* Uses Maven;
* Has Unit tests;
* Has a (small) text description on how to use/run this application;