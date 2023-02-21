# Challenge BE Application

I was presented to a challenge which consist on the enhancement of a simple REST API (HTTP/JSON), that will be consumed by a Front-End application.

## Technologies:

- Java 11
- Maven
- Spring framework
- Database: H2 (Embedded in memory)

The main goal of this exercise is to assess my approach to problem solving, as well as my ability to analyze, debug and write clean, testable and reusable code.

The API provides endpoints for the CRUD operations and an additional endpoint that returns a list of Movies filtered by launch date.

My tasks are the following:

- Identify and fix the issues that are preventing the server from starting
- Analyze the provided code, identify and implement improvements I consider relevant
- Implement pagination in whichever endpoints I find appropriate
- Add a new resource “Actor” to the platform:
	- Resource: Actor
	- Attributes:
		- Name - Name of the Actor
		- Birth Date - The birthdate of the Actor
		- Gender - Gender of the Actor (Male/Female)
	- Relation of Actor with Movie is “a movie may have from 1 to n actors playing in”
	- Implement the CRUD endpoints for the new Actor resource
	- Implement the additional endpoint:
		- given a movie id, return the list of actors that play in that movie

### Steps to run the application:

1. Clone the repository from github
2. Build the application using maven -> `mvn clean install`
3. Run the application -> `mvn spring-boot:run` -> The application will start and you can access it at http://localhost:8080
4. To test the application, you can use a tool such as Postman or cURL to send requests to the API endpoints

## MOVIES

To manage Movies, here are some considerations:

- The title must not be empty;
- The rank must not be empty and it should be decimal number between 0 and 10;
- The date should not be empty and it should follow the local date format;
- The revenue is not mandatory, if included, should be a positive number;

## Movies endpoint: `/movies`

### Get All Movies (pageable)

#### Get /movies

Returns a list of movies giving page and size parameters. These parameters are not required, the default values are page=0 and size=10.

Example: localhost:8080/movies?page=0&size=10

### Get Movie by ID 

#### Get /movies/{id}

Returns a movie given an id.

Example: localhost:8080/movies/1

### Get Movies by Date

#### Get /movies/date

Returns a list of movies filtered by date parameter. This parameter is required.

Example: localhost:8080/movies/date?date=1985-07-03

### Create Movie

#### Creates a new movie. Should be sent with a movie object. Only the revenue column is not required. Will return the created object.

Example: localhost:8080/movies

Object example:
{
"title": "Movie Title",
"date": "1994-09-10",
"rank": 9.3,
"revenue": 25000000
}

### Update Movie

#### Put /movies

Updates a movie. Should be sent with a movie object. Will return the updated object.

Example: localhost:8080/movies

Object example:
{
"id": "1",
"title": "Movie Title",
"date": "1994-09-10",
"rank": 9.3,
"revenue": 25000000
}

### Delete Movie

#### Delete /movies/{id}

Deletes a movie given an id.

Example: localhost:8080/movies/1


## ACTORS

To manage Actors, here are some considerations:

- The name must not be empty;
- The bdate should not be empty and it should follow the local date format;
- The gender must not be empty and it should be "male" or "female";
- The movieId must not be empty, this clomun will create a relation of Actor with Movie: A movie may have from 1 to n actors playing in;
- For the sake of simplicity, one actor can only be playing in one movie and if a movie is deleted, all acotrs associated will be automatically deleted as well.

## Actors endpoint: `/actors`

### Get All Actors (pageable)

#### Get /actors

Returns a list of actors giving page and size parameters. These parameters are not required, the default values are page=0 and size=10.

Example: localhost:8080/actors?page=0&size=10

### Get Actor by ID 

#### Get /actors/{id}

Returns an actor given an id.

Example: localhost:8080/actors/1

### Get Actors by Movie

#### Get /actors/movie

Returns a list of actors of actors filtered by movie id.

Example: localhost:8080/actors/movie/1

### Create Actor

#### Creates a new actor. Should be sent with an actor object. All columns are required. Will return the created object.

Example: localhost:8080/actors

Object example:
{
"name": "Actor",
"bdate": "1974-10-26",
"gender": "male",
"movieId": "1"
}

### Update Actor

#### Put /actors

Updates an actor. Should be sent with an actor object. Will return the updated object.

Example: localhost:8080/actors

Object example:
{
"id": "1",
"name": "Actor",
"bdate": "1974-10-26",
"gender": "male",
"movieId": "1"
}

### Delete Actor

#### Delete /actors/{id}

Deletes an actor given an id.

Example: localhost:8080/actors/1
