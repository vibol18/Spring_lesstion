# School Management System API

A comprehensive, production-ready Spring Boot backend for managing all aspects of a school ecosystem, from academics and student enrollment to hostel allocation, timetable scheduling, exams, library, transport, and billing.

## 🚀 Key Modules Included

1. **Academic** — Manage Academic Years, Classes, Sections, Subjects, and enrollments.
2. **Student & Parent** — Handle student admissions, profile creation, and parent associations.
3. **Teacher & Staff** — Maintain employee files, job profiles, and department categorization.
4. **Attendance** — Track daily attendance records for both students and staff.
5. **Timetable** — Manage classroom, subject, and teacher schedules.
6. **Exams & Results** — Schedule term exams, record grades/marks, and generate student report cards.
7. **Assignments** — Publish coursework and collect submissions.
8. **Fee & Payments** — Set up billing categories and log cash or online payment transactions.
9. **Library** — Track books inventory, issue requests, due dates, and fines.
10. **Hostel** — Allocate rooms, manage hostel blocks, and track occupants.
11. **Transport** — Route mapping, bus stops, driver details, and student assignments.
12. **Communication** — Broadcast notices, schedule events, send direct messages, and apply for leaves.
13. **Security & Notification** — Token-based JWT authorization and automated event-based audit logging.

---

## 🛠️ Technology Stack

- **Java 17** with **Spring Boot 3**
- **Spring Security** & **JSON Web Tokens (JWT)** for role-based security
- **Spring Data JPA** with **PostgreSQL**
- **Flyway** for database migrations (`src/main/resources/db/migration/`)
- **Docker** & **Docker Compose** for easy local orchestration
- **Lombok** for boilerplate reduction
- **Swagger / OpenAPI 3** for interactive API documentation

---

## 🚦 Getting Started

### Prerequisites
- Docker & Docker Compose
- JDK 17+ and Maven (if running locally without Docker)

### Run with Docker Compose
To boot up both the application and the PostgreSQL database in containers:
```bash
docker compose -f docker/docker-compose.yml up --build
```
The API will be available at `http://localhost:8080`.
Swagger UI documentation will be hosted at `http://localhost:8080/swagger-ui/index.html`.

### Run Locally (Development Profile)
1. Ensure a PostgreSQL instance is running on port 5432 with a database named `school_dev`.
2. Run using the Maven wrapper:
```bash
./mvnw spring-boot:run
```
