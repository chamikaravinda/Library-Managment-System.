# Library Management System - Spring Boot API

This is a **Library Management System API** built using **Spring Boot**, **MySQL**, and **JWT-based Authentication**. It allows librarians and borrowers to manage books, borrowing records, and authentication securely.


---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/library-management-api.git
cd library-management-api
```

### 2. Configure Environment Variables

You can either export them in your shell or use a `.env` file with Docker.

```bash

export ENVIRONMENT=dev
export DB_URL=jdbc:mysql://localhost:3306/library_system
export DB_USERNAME=root
export DB_PASSWORD=admin
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

### 4. Create Users
Run the query in resources/data.sql file to create users

### 5. Access the API

* Base URL: `http://localhost:8080`
* Auth: `POST /api/auth/login`

You can use requests.http file and invoke the endpoints from intelliJ Idea


