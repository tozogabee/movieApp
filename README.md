# 🎬 Movie API

This Spring Boot application provides RESTful APIs to search, retrieve, and filter movie data. It uses an **API Key system** for endpoint security.

## 🔐 API Key Usage

All requests must include an API key as a query parameter:

```
?api_key=YOUR_API_KEY
```

> ✅ **NOTE**: The API key is printed to the console logs when the Spring Boot application starts.  
Keep it **secure** and **do not commit it to version control**.

---

## 📌 Base URL

```
http://localhost:8080
```

---

## 📖 Endpoints

### 🔎 1. Search Movies

**URL**: `/movie/search`  
**Method**: `GET`  
**Required Query Parameter**:  
- `query`: (e.g. `"Batman"`)

**Optional Parameters**:  
- `sort_by`: `rating` | `releaseDate`  
- `filter`: e.g. `"genre=Action"`, `"language=English"`

**Example**:
```
GET /movie/search?query=batman&sort_by=rating&filter=genre=Action&api_key=YOUR_API_KEY
```

---

### 🎬 2. Get Movie by ID

**URL**: `/movie/{id}`  
**Method**: `GET`  
**Path Variable**: UUID of the movie  

**Example**:
```
GET /movie/e8e72854-d1ed-4681-840d-576fd1ceb5b8?api_key=YOUR_API_KEY
```

---

### 🌟 3. Popular Movies (Top 50 with pagination)

**URL**: `/movie/popular`  
**Method**: `GET`  
**Optional Query**:
- `page`: Page number (starting from `0`)

**Returns**: 10 movies per page from the top 50 most rated.

**Example**:
```
GET /movie/popular?page=1&api_key=YOUR_API_KEY
```

---

### 🧪 4. H2 Console (Dev only)

**URL**: `/h2-console` 

***Username***: sa

***Password***: sa

Useful for database debugging during development.

---

## 📘 Swagger UI

Interactive API documentation available at:

```
http://localhost:8080/swagger-ui/index.html
```

---
## 


## 📁 Tech Stack

- Java 21
- Spring Boot 3.2.5
- Spring Security
- Spring Data JPA
- MapStruct
- H2 / MySQL (runtime switchable)
- Swagger (springdoc-openapi)
- Liquibase

---

## 🚀 Running the App

1. Build:
```bash
./mvnw clean install
```

2. Run:
```bash
./mvnw spring-boot:run
```

3. Check the logs for:
```
✅ Generated API Key: 12345-abcde-...
```

Use that key in all your requests as `?api_key=...`.

---

## 🗃️ Database Schema

The Movie entity

| Field Name    | Type      | Description             |
|---------------|-----------|-------------------------|
| `id`          | UUID      | PRIMARY KEY             |
| `title`       | String    | movie title             |
| `releaseDate` | LocalDate | release-date(YYYY-MM-DD |
| `posterUrl`   | String    | poster URL              |
| `rating`      | Double    | rating(0-10.0)          |
| `genres`      | String    | genre                   |
| `language`    | String    | language                |

> Using Hibernate JPA. A Liquibase handles the migration schemas (`resources/db/changelog/...`).

## ✅ How to Test the API

1. **Launch the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

2. **Find the api key in the console log**:
    ```
    ✅ Generated API Key: 12345-abcde-...
    ```

3. **Testing the endpoint from POSTMAN or Insomnia**:

    - **Example queries with api key**:

      ```
      GET http://localhost:8080/movie/search?query=Fin&filter=genre=horror&api_key=ltJktQn7mquJYJyV931AO-N0cry4QKl8
      GET http://localhost:8080/movie/search?query=Fin&filter=genre=horror&sort_by=rating&api_key=miBzRqa5fplSBNKaGurDFun2ig3EOqvb
      GET /movie/popular?page=0&api_key=12345-abcde-...
      GET /movie/e8e72854-d1ed-4681-840d-576fd1ceb5b8?api_key=12345-abcde-...
      ```

4. **Check the datas with h2 console**

## 📬 Contact

**Developer**: Gábor Tóth  
📧 tozogabee@gmail.com  
🔗 [GitHub Repo](https://github.com/tozogabee/movieApp)
