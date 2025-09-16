# Simple Library Management System API

## Overview
This is a simplified Library Management System that demonstrates:
1. **Basic CRUD operations** with local database
2. **External API integration** with Open Library API
3. **Data import** from external API to local database

## Base URL
```
http://localhost:8080/api/books
```

## 🏠 Local Database CRUD Operations

### 1. Get All Books
- **GET** `/api/books`
- **Description**: Retrieve all books from local database
- **Response**: Array of book objects

### 2. Get Book by ID
- **GET** `/api/books/{id}`
- **Description**: Retrieve a specific book by its ID
- **Parameters**: `id` (Long) - Book ID
- **Response**: Book object or 404 if not found

### 3. Search Books
- **GET** `/api/books/search?keyword={keyword}`
- **Description**: Search books in local database
- **Parameters**: `keyword` (String) - Search term
- **Response**: Array of matching book objects

### 4. Create Book
- **POST** `/api/books`
- **Description**: Add a new book to local database
- **Request Body**: Book object (JSON)
- **Response**: Created book object

### 5. Update Book
- **PUT** `/api/books/{id}`
- **Description**: Update an existing book
- **Parameters**: `id` (Long) - Book ID
- **Request Body**: Book object (JSON)
- **Response**: Updated book object

### 6. Delete Book
- **DELETE** `/api/books/{id}`
- **Description**: Remove a book from local database
- **Parameters**: `id` (Long) - Book ID
- **Response**: 204 No Content

## 🌐 External API Integration

### 7. Search External Books
- **GET** `/api/books/external/search?title={title}`
- **Description**: Search books from Open Library API
- **Parameters**: `title` (String) - Book title to search
- **Response**: Array of external book objects

### 8. Import External Book
- **POST** `/api/books/external/import`
- **Description**: Import a book from external API to local database
- **Request Body**: External book object (JSON)
- **Response**: Created book object in local database

## 📋 Data Structures

### Local Book Object
```json
{
  "id": 1,
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "isbn": "978-0-7432-7356-5",
  "publicationYear": 1925,
  "isAvailable": true,
  "createdAt": "2025-09-14T08:28:02.094",
  "updatedAt": "2025-09-14T08:28:02.094"
}
```

### External Book Object
```json
{
  "title": "Effective Java",
  "author_name": ["Joshua Bloch"],
  "isbn": ["978-0134685991"],
  "first_publish_year": 2001,
  "firstAuthor": "Joshua Bloch",
  "firstIsbn": "978-0134685991"
}
```

## 🧪 Sample Usage

### 1. Get all local books
```bash
curl -X GET http://localhost:8080/api/books
```

### 2. Create a new book
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Sample Book",
    "author": "Sample Author",
    "isbn": "978-0-123456789-0",
    "publicationYear": 2023
  }'
```

### 3. Search external books
```bash
curl -X GET "http://localhost:8080/api/books/external/search?title=java"
```

### 4. Import external book to local database
```bash
curl -X POST http://localhost:8080/api/books/external/import \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java",
    "author_name": ["Joshua Bloch"],
    "first_publish_year": 2001
  }'
```

## 🎯 Key Features Demonstrated

1. **RESTful API Design** - Standard HTTP methods and status codes
2. **Database Operations** - CRUD operations with SQLite
3. **External API Integration** - HTTP calls to third-party services
4. **Data Transformation** - Converting external data to local format
5. **Error Handling** - Proper exception handling and HTTP status codes
6. **Validation** - Input validation with Jakarta Validation

## 🚀 Quick Start

1. **Start the application**: `mvn spring-boot:run`
2. **Test local CRUD**: Use endpoints 1-6 above
3. **Test external API**: Use endpoints 7-8 above
4. **View database**: Check `library.db` file in project root

This simplified version focuses on the core concepts needed for a 45-minute interview demonstration.
