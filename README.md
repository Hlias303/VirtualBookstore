# 📚 Virtual Bookstore

A full-stack bookstore application with user authentication, book management, and an AI-powered recommendation system built with **Spring Boot**.

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| **Java 21** | Programming language |
| **Spring Boot 3.4** | Backend framework |
| **Spring Security + JWT** | Authentication & authorization |
| **Spring Data JPA** | Database ORM |
| **PostgreSQL** | Database |
| **React (Vite)** | Frontend |
| **Lombok** | Boilerplate reduction |

## ✨ Features

- 🔐 JWT authentication with Admin/User roles
- 📖 Full book CRUD with image upload support
- 🔍 Book search by keyword
- ⭐ User ratings & sentiment reviews
- 🤖 Slope One collaborative filtering recommendations
- 🌐 CORS configured for React frontend

## 🚀 Getting Started

### Prerequisites

- Java 21+
- PostgreSQL
- Maven (or use included `mvnw`)

### Setup

1. **Clone the repository**
```bash
git clone https://github.com/your-username/VirtualBookstore.git
cd VirtualBookstore
```

2. **Create the database**
```sql
CREATE DATABASE virtualbookstore;
```

3. **Configure application properties** in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/virtualbookstore
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Run the application**
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## 📡 API Endpoints

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/Register` | Public | Register a new user |
| POST | `/Register/Admin` | Admin | Register an admin |
| POST | `/login` | Public | Login & get JWT token |
| GET | `/Books` | Public | List all books |
| GET | `/Books/{id}` | Public | Get book details |
| GET | `/Books/search?keyword=` | Public | Search books |
| POST | `/AddBookImage` | Admin | Add book with image |
| PUT | `/Books/{id}` | Public | Update book |
| DELETE | `/Books/{id}` | Admin | Delete book |
| GET | `/Users` | Admin | List all users |
| DELETE | `/Users/{id}` | Public | Delete user |
| GET | `/UserBooks` | JWT | List user ratings |
| POST | `/Books/AddSentiment` | JWT | Rate a book |
| GET | `/Recommendations` | JWT | Get personalized recommendations |

### Example

```bash
# Login
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Get books with token
curl http://localhost:8080/Books \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## 🏗️ Project Structure

```
src/main/java/com/example/VirtualBookstore/
├── Config/           # Security config, JWT filter
├── Controller/       # REST controllers
├── Model/            # JPA entities
├── Repo/             # Data repositories
└── Service/
    ├── Interface/        # Service contracts
    └── Implementation/   # Service implementations
```

## 📖 Recommendation Algorithm

The app uses **Slope One** (item-based collaborative filtering):

1. Builds difference matrices between book pairs from user ratings
2. Predicts a user's rating for unrated books using weighted averages
3. Returns top-N books the user hasn't rated yet

## 📄 License

This project is licensed under the MIT License.
