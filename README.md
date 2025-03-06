Parking Management System
Description

This is a Spring Boot application that manages a parking system. It includes functionalities to manage parking lots, parking spaces, tickets, vehicles, and owners.
Technologies Used

Setup and Installation
1. Clone the Repository

git clone git@github.com:danielramonreinalopez/Parking-lot-application-in-Java-with-JPA-and-Docker.git
cd parking-management

2. Configure the Database
   Update the src/main/resources/application.properties file with your MySQL configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/parking_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3. Build the Project

mvn clean install

4. Run the Application

mvn spring-boot:run

Or, if you use Docker:

docker-compose up --build

5. API Endpoints

The application provides RESTful APIs to manage the parking system. Use Postman or any API client to test the following endpoints:

Example
Parking Lots
GET /api/parking-lots - Get all parking lots
POST /api/parking-lots - Create a new parking lot
PUT /api/parking-lots/{id} - Update a parking lot
DELETE /api/parking-lots/{id} - Delete a parking lot

Troubleshooting
Make sure MySQL is running and the credentials are correct.
