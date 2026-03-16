
# EduRoster API

A simple RESTful API for managing Schools and Students, built with Spring Boot and PostgreSQL.

## Technologies Used
* Java
* Spring Boot (Web, Data JPA, Validation)
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
  * **Validation:** `firstName` and `lastName` are required. Returns `400 Bad Request` with field-level error messages on failure.
* `GET /students` : Retrieves a list of all students.
* `GET /students/{student-id}` : Retrieves a specific student by their ID.
* `GET /students/search/{student-name}` : Searches for students by their first name.
* `DELETE /students/{student-id}` : Deletes a specific student by their ID.

## Validation & Error Handling
Request body validation is enforced using Jakarta Bean Validation (`@NotEmpty`). Invalid requests return a `400 Bad Request` response with a JSON map of field names to error messages. Example:
```json
{
  "firstName": "Firstname should not be empty.",
  "lastName": "Lastname should not be empty."
}
```
## Testing
Unit tests are provided for:
* **`StudentMapperTest`** — Tests mapping between DTOs and entities, including null-safety.
* **`StudentServiceTest`** — Tests service-layer logic (save, find all, find by ID, search by name, delete) with mocked repository and mapper dependencies using Mockito.

Run tests with:
```bash
./mvnw test
```