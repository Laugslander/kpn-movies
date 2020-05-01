# KPN Development Assignment
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

## How to run the application

* Run the Spring Boot application
* On start up, the application imports the data from the `movies.xml` and `profiles.json` files using Spring Batch
* To query the application for suggestions, use the REST endpoint `'/api/yourapp/v1/movie/suggestion/customer/id/{id}'` 