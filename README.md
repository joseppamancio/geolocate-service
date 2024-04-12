[Português](README.pt.md) | [Español](README.es.md)


[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![Spring WebFlux](https://img.shields.io/badge/Spring_WebFlux-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
[![Project Reactor](https://img.shields.io/badge/Project_Reactor-BA0D34?style=for-the-badge&logo=projectreactor&logoColor=white)](https://projectreactor.io/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![WireMock](https://img.shields.io/badge/WireMock-E95420?style=for-the-badge&logo=wireguard&logoColor=white)](http://wiremock.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)](https://swagger.io/)

# Geolocation with Spring WebFlux
## 📝 Description
This project is a Reactive Rest API using Spring WebFlux that aims to return the location through geographic coordinates (latitude and longitude) of an address.

WebFlux is a reactive framework that is part of Spring 5 and is an alternative to Spring MVC. It is based on Project Reactor, which is a reactive implementation for Java.
It is suitable for applications that require high scalability and low latency, such as IoT applications, chat, online games, among others.
Because it is non-blocking, it is capable of handling a large number of simultaneous requests with fewer threads, making it more efficient.

## ⚙️ Technologies
- Java 17
- Spring WebFlux
- Project Reactor
- Maven
- MongoDB
- Docker
- WireMock

## ✅ Prerequisites

### Docker
Before you begin, you will need to have Docker and Docker Compose installed on your machine.
- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

The project uses Docker to run 3 containers:
- MongoDB: NoSQL database used to store data
- Mongo Express: Graphical interface to view MongoDB data
- WireMock: Tool to simulate RESTful services, which will simulate the Google geolocation API

### Postman
To test the Endpoints, import the Collection and the JSON Environments into Postman, which can be found in the following directories:
- 'src/main/resources/docs/collections/geolocatesvc.postman_collection.json'.
- 'src/main/resources/docs/collections/environments/LOCAL.postman_environment.json'.

## 📦 Installation
1. Clone the repository

```bash
  git clone https://github.com/joseppamancio/geolocatesvc.git
```

2. Import the project into your IDE

3. Run the project
   When running the project, in addition to starting the application, the MongoDB, Mongo Express, and WireMock containers will be started.
   WireMock will simulate the Google geolocation API, and MongoDB will be used to store the data.

4. Testing the Endpoints
   With the collection and environment imported into Postman, you can test the API Endpoints.
   3 Endpoints have been made available:

### Endpoint to check if the application is up
- **GET {{HOSTNAME}}/geolocatesvc/ping**

### Endpoint to check if WireMock is up
- **GET {{WIREMOCK}}/maps/api/geocode/json?key=_Ab12Cd34Ef56Gh78Ij90Kl12Mn34Op56-r8St9&latlng=-26.1965843,-52.6890572
  ***Note 1:*** The GOOGLE_API_KEY is just an example, not a valid key. But it follows the pattern of a valid key.

This endpoint will return a JSON with the location of the address corresponding to the geographic coordinates provided.
***Note 2:*** The coordinates provided are just an example, a fixed address was mocked to simulate the response from the Google API.

### Endpoint to fetch the location of an address through geographic coordinates
- **GET {{HOSTNAME}}/geolocatesvc/api/v1/address?latitude=-26.1965843&longitude=-52.6890572

Finally, this endpoint of our API will return the location of the address corresponding to the provided geographic coordinates,
it will fetch information from the Google API (Wiremock in this case) and store it in MongoDB.


5. Accessing MongoDB
   After obtaining the latitude and longitude value, the application will store it in MongoDB.
   To access MongoDB, access the URL `http://localhost:8081/` and use the following credentials:
- **Username: admin**
- **Password: notset**
  You will have access to the `geolocatesvc` database and the `locations` collection through the Mongo Express graphical interface.

## 🧪 End-to-End Tests
The application has an end-to-end test where, upon making a request to the location search endpoint,
the application will retrieve information from WireMock and return the location of the address corresponding to the provided geographic coordinates.

## 📚 Documentation
The API documentation was done using Swagger and can be accessed through the URL `http://localhost:8080/geolocatesvc/swagger-ui.html`.

# 🎥 Demonstration Video
https://github.com/joseppamancio/geolocatesvc/assets/54041843/acf436a7-e094-4bfb-92ca-ca5bc6c3a648

