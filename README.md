# 🎬 Movie API

This Spring Boot application provides RESTful APIs to search, retrieve, and filter movie data. It uses an **API Key system** for endpoint security.

## 🔐 API Key Usage

All requests must include an API key as a query parameter:

```
?api_key=YOUR_API_KEY
```

> ✅ **NOTE**: The API key is printed to the console logs when the Spring Boot application starts.  
Keep it **secure** and **do not share it**.

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
***Request***:
```
GET /movie/search?query=fin&sort_by=rating&filter=genre=horror&api_key=YOUR_API_KEY
```

***Response***:
```
[
	{
		"id": "da40216f-0128-4df9-ac13-68419859e040",
		"title": "Final Warrior",
		"releaseDate": "1997-10-30",
		"posterUrl": "https://example.com/posters/final_warrior.jpg",
		"averageRating": 7.5
	},
	{
		"id": "762af8f8-1d8f-4f6f-853b-8ea8ca05cb95",
		"title": "Final Journey",
		"releaseDate": "1995-04-08",
		"posterUrl": "https://example.com/posters/final_journey.jpg",
		"averageRating": 5.1
	},
	{
		"id": "29ce9b33-287a-4d24-a68b-878f5779ddaf",
		"title": "Final Memory",
		"releaseDate": "2013-10-08",
		"posterUrl": "https://example.com/posters/final_memory.jpg",
		"averageRating": 4.9
	}
]
```

---

### 🎬 2. Get Movie by ID

**URL**: `/movie/{id}`  
**Method**: `GET`  
**Path Variable**: UUID of the movie  

**Example**:
***Request***:
```
GET /movie/e8e72854-d1ed-4681-840d-576fd1ceb5b8?api_key=YOUR_API_KEY
```

***Response***:
```
{
	"title": "Hidden Journey",
	"releaseDate": "2003-06-09",
	"fullPosterUrl": "https://example.com/posters/hidden_journey.jpg",
	"overview": "Worry choice real truth culture true court.",
	"genres": "Sci-Fi, Adventure",
	"averageRating": 7.9,
	"runtime": 81,
	"language": "ja"
}
```

---

### 🌟 3. Popular Movies (Top 50 with pagination)

**URL**: `/movie/popular`  
**Method**: `GET`  
**Optional Query**:
- `page`: Page number (starting from `0`)

**Returns**: 10 movies per page from the top 50 most rated.

**Example**:

***Request***:
```
GET /movie/popular?page=1&api_key=YOUR_API_KEY
```
***Response***
```
[
	{
		"id": "796ec3bd-cb17-4322-b3d4-234b9ecc89ab",
		"title": "Last Code",
		"releaseDate": "1988-04-06",
		"posterUrl": "https://example.com/posters/last_code.jpg",
		"averageRating": 8.6
	},
	{
		"id": "4412c36d-cf41-4bb4-9744-4f28818ac477",
		"title": "Last Warrior",
		"releaseDate": "1984-10-25",
		"posterUrl": "https://example.com/posters/last_warrior.jpg",
		"averageRating": 8.5
	},
	{
		"id": "b8b788aa-bca0-4768-b4df-0b7040658402",
		"title": "Blue Warrior",
		"releaseDate": "1999-08-29",
		"posterUrl": "https://example.com/posters/blue_warrior.jpg",
		"averageRating": 8.5
	},
	{
		"id": "c5681071-e309-46a5-8bd2-9c756ccccdc6",
		"title": "Hidden Warrior",
		"releaseDate": "1985-06-07",
		"posterUrl": "https://example.com/posters/hidden_warrior.jpg",
		"averageRating": 8.5
	},
	{
		"id": "03053787-d636-4022-92b3-67c66a991662",
		"title": "Dark Revenge",
		"releaseDate": "1984-04-01",
		"posterUrl": "https://example.com/posters/dark_revenge.jpg",
		"averageRating": 8.4
	},
	{
		"id": "faf7f9a6-1777-4f0b-aa14-dc234dac3129",
		"title": "Final Legacy",
		"releaseDate": "2011-11-28",
		"posterUrl": "https://example.com/posters/final_legacy.jpg",
		"averageRating": 8.4
	},
	{
		"id": "cee21520-2ff1-4f1c-b76e-17ffb35681bd",
		"title": "Last Revenge",
		"releaseDate": "2020-12-29",
		"posterUrl": "https://example.com/posters/last_revenge.jpg",
		"averageRating": 8.3
	},
	{
		"id": "ea5a3a32-8214-4d5e-952b-49a491fd1ff7",
		"title": "Dark Road",
		"releaseDate": "2006-05-17",
		"posterUrl": "https://example.com/posters/dark_road.jpg",
		"averageRating": 8.3
	},
	{
		"id": "ba83b52c-769c-4be7-83a0-40386534d4da",
		"title": "Hidden Empire",
		"releaseDate": "2002-01-28",
		"posterUrl": "https://example.com/posters/hidden_empire.jpg",
		"averageRating": 8.3
	},
	{
		"id": "2ecd3dac-140c-4bdc-a5f8-88a85f0a897b",
		"title": "Glorious Road",
		"releaseDate": "2002-08-16",
		"posterUrl": "https://example.com/posters/glorious_road.jpg",
		"averageRating": 8.2
	}
]
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
Get the testable element, check fields and UUID.

## 📬 Contact

**Developer**: Gábor Tóth  
📧 tozogabee@gmail.com  
🔗 [GitHub Repo](https://github.com/tozogabee/movieApp)
