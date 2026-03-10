# REST Demo API

A simple RESTful API for managing Schools and Students, built with Spring Boot and PostgreSQL.

## Technologies Used
* Java
* Spring Boot (Web, Data JPA)
* PostgreSQL
* Maven

## Setup & Installation
1. Start your PostgreSQL server and create a database named `demo`.
2. Update the database credentials (username and password) in `src/main/resources/application.yml` if necessary (Defaults: `postgres` / `admin`).
3. Run the application. Database tables will be generated automatically via Hibernate's `ddl-auto: update` property.

## API Endpoints

### Schools
* `POST /schools` : Creates a new school.
    * Body: `{"name": "School Name"}`
* `GET /schools` : Retrieves a list of all schools.

### Students
* `POST /students` : Creates a new student.
    * Body: `{"firstName": "John", "lastName": "Doe", "email": "john@example.com", "schoolId": 1}`
* `GET /students` : Retrieves a list of all students.
* `GET /students/{student-id}` : Retrieves a specific student by their ID.
* `GET /students/search/{student-name}` : Searches for students by their first name.
* `DELETE /students/{student-id}` : Deletes a specific student by their ID.