# springboot-microservices-demo
This repository contains two standalone Spring Boot services:

- **student-service:** Manages student registration and lookup
- **fees-service:** Handles fee collection and student fee history

Each service is independently runnable and exposes RESTful APIs with Swagger documentation.

-----------------------
**1. Student Service**

	## Run Student Service

	cd student-service
	mvn spring-boot:run


**2. Fees Service**

	## Run Fees Service
	
	cd fees-service
	mvn spring-boot:run

	
**API Documentation (Swagger)**

	Both services use Springdoc OpenAPI for auto-generated Swagger documentation.
	
	- **Student Service:** http://localhost:8080/swagger-ui/index.html
	
	- **Fees Service:** http://localhost:9090/swagger-ui/index.html


**Postman Collections**
You can test the APIs using Postman. Import the following sample requests:
Student Service
	- POST /api/students/add — Add a student
	- GET /api/students/{id} — Get student by ID
	- PUT /api/students/update/{id} — Update student
	- GET /api/students -Get all students

Fees Service
	- POST /api/fees —Submit fee payment
	- GET /api/fees/student/{studentId} — Get fee history for student
	- GET /api/fees/{id}  —Get receipt by receipt id.
	
**Tech Stack**

	- Java 21
	
	- Spring Boot 3.5.7
	
	- Spring Web – for building RESTful APIs
	
	- Spring Data JPA – for database access
	
	- H2 Database – in-memory DB for testing
	
	- RestTemplate – for inter-service HTTP calls
	
	- Resilience4j Circuit Breaker – for fault tolerance and fallback handling
	
	- Springdoc OpenAPI – for Swagger documentation
	
	- JUnit5 + Mockito – for unit testing

**Testing**

	Run unit tests with:
	mvn test

