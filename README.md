# Recipe Management API

A RESTful backend API built with Spring Boot for managing recipes, ingredients, and ratings — with JWT-based user authentication.

## Features

- 🔐 **JWT Authentication** — user registration and login with secure, token-based authentication
- 🍲 **Recipe Management** — full CRUD operations for recipes
- 🥕 **Ingredients** — manage ingredients associated with each recipe
- ⭐ **Ratings** — users can rate recipes
- 📊 **Difficulty Levels** — recipes are categorized by difficulty
- ⚠️ **Global Exception Handling** — centralized error handling with custom exceptions (e.g. duplicate email, invalid credentials)

## Tech Stack

- Java
- Spring Boot
- Spring Security + JWT
- Gradle
- PostgreSQL

## Architecture

- `entity` — JPA entities (User, Recipe, Ingredient, Rating, Difficulty)
- `repository` — Spring Data JPA repositories
- `service` / `service/impl` — business logic layer
- `security` — JWT filter, authentication, and user details services
- `exception` — global exception handling

## How to Run

```bash
git clone https://github.com/fidanetagizade/recipe-management-api.git
cd recipe-management-api
```

Configure your database and JWT secret in `src/main/resources/application.properties`, then run:

```bash
./gradlew bootRun
```
