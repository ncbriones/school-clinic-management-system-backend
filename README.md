## School Clinic Management System Backend (Java, Spring Boot, Spring Data JPA, Spring Security, MySQL)

This is the backend of the School Clinic Management System, built using **Java**, **Spring Boot**, **Spring Data JPA**, **Spring Security** , and **MySQL**. It helps school nurses and clinic staff track student visits, medical logs, illness management, and medicine inventory in real time.

### Key Features

• Role-Based Access Control (RBAC): Implemented separate functionalities and dashboards for System Administrators and School Nurses.

• Spring Security Utilization: Secured RESTful API endpoints utilizing JSON Web Tokens (JWT) for stateless authentication, combined with password encoding for cryptographic password hashing.

• Low Inventory Alert: Implemented inventory monitoring that automatically flags low inventory stocks, preventing critical medical supply deficits.

### Project Structure

The code is split up strictly to keep the application organized and ensure that instant hot-reloading works without bugs:

```
text
src/main/java/com/scms/api/
├── config/             # Security filter chain and global utility bean configurations
├── controller/         # REST API endpoints handling incoming HTTP requests
├── dto/                # Data Transfer Objects for filtering incoming/outgoing payloads
├── entity/             # Database entities  
├── exception/          # Global exception handlers
├── repository/         # Spring Data JPA repository interfaces
├── security/           # JWT services & auth filters, CustomUserDetails, CustomUserDetailsService, and authentication managers
└── service/            # Business logic layer
```

### Tech Stack & Tool Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security

### Setup & Run

**1. Install dependencies & build:**

```
bash
./mvnw clean install
```

**2. Run the Spring Boot application:**

```
bash
./mvnw spring-boot:run
```
