# Library Management System API Documentation

## Base URL
```
http://localhost:8080/api/books
```

## Endpoints

### 1. Get All Books
- **GET** `/api/books`
- **Description**: Retrieve all books in the library
- **Response**: Array of book objects

### 2. Get Book by ID
- **GET** `/api/books/{id}`
- **Description**: Retrieve a specific book by its ID
- **Parameters**: `id` (Long) - Book ID
- **Response**: Book object or 404 if not found

### 3. Get Book by ISBN
- **GET** `/api/books/isbn/{isbn}`
- **Description**: Retrieve a book by its ISBN
- **Parameters**: `isbn` (String) - Book ISBN
- **Response**: Book object or 404 if not found

### 4. Search Books
- **GET** `/api/books/search?keyword={keyword}`
- **Description**: Search books by title, author, or ISBN
- **Parameters**: `keyword` (String) - Search term
- **Response**: Array of matching book objects

### 5. Get Books by Author
- **GET** `/api/books/author/{author}`
- **Description**: Get all books by a specific author
- **Parameters**: `author` (String) - Author name
- **Response**: Array of book objects

### 6. Get Available Books
- **GET** `/api/books/available`
- **Description**: Get all available books (not borrowed)
- **Response**: Array of available book objects

### 7. Get Unavailable Books
- **GET** `/api/books/unavailable`
- **Description**: Get all borrowed books
- **Response**: Array of unavailable book objects

### 8. Create Book
- **POST** `/api/books`
- **Description**: Add a new book to the library
- **Request Body**: Book object (JSON)
- **Response**: Created book object or 400 if validation fails

### 9. Update Book
- **PUT** `/api/books/{id}`
- **Description**: Update an existing book
- **Parameters**: `id` (Long) - Book ID
- **Request Body**: Book object (JSON)
- **Response**: Updated book object or 404 if not found

### 10. Delete Book
- **DELETE** `/api/books/{id}`
- **Description**: Remove a book from the library
- **Parameters**: `id` (Long) - Book ID
- **Response**: 204 No Content or 404 if not found

### 11. Borrow Book
- **POST** `/api/books/{id}/borrow`
- **Description**: Mark a book as borrowed
- **Parameters**: `id` (Long) - Book ID
- **Response**: Updated book object or 400 if not available

### 12. Return Book
- **POST** `/api/books/{id}/return`
- **Description**: Mark a book as returned
- **Parameters**: `id` (Long) - Book ID
- **Response**: Updated book object or 404 if not found

### 13. Search External Books
- **GET** `/api/books/external/search?query={query}`
- **Description**: Search for books from Open Library API
- **Parameters**: `query` (String) - Search term
- **Response**: Array of external book objects

### 14. Search External Books by Title
- **GET** `/api/books/external/search/title?title={title}`
- **Description**: Search external books by title
- **Parameters**: `title` (String) - Book title
- **Response**: Array of external book objects

### 15. Search External Books by Author
- **GET** `/api/books/external/search/author?author={author}`
- **Description**: Search external books by author
- **Parameters**: `author` (String) - Author name
- **Response**: Array of external book objects

### 16. Import External Book
- **POST** `/api/books/external/import`
- **Description**: Import a book from external API to local database
- **Request Body**: External book object (JSON)
- **Response**: Created book object or 400 if validation fails

## Book Object Structure
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

## External Book Object Structure
```json
{
  "title": "Effective Java",
  "author_name": ["Joshua Bloch"],
  "isbn": ["978-0134685991"],
  "first_publish_year": 2001,
  "number_of_pages_median": 416,
  "subject": ["Java (Computer program language)", "Programming"],
  "key": "/works/OL6223299W",
  "firstAuthor": "Joshua Bloch",
  "firstIsbn": "978-0134685991"
}
```

## Sample Requests

### Create a new book:
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

### Search for books:
```bash
curl -X GET "http://localhost:8080/api/books/search?keyword=Gatsby"
```

### Borrow a book:
```bash
curl -X POST http://localhost:8080/api/books/1/borrow
```

### Search external books:
```bash
curl -X GET "http://localhost:8080/api/books/external/search?query=java"
```

### Search external books by author:
```bash
curl -X GET "http://localhost:8080/api/books/external/search/author?author=Joshua"
```

### Import external book:
```bash
curl -X POST http://localhost:8080/api/books/external/import \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java",
    "author_name": ["Joshua Bloch"],
    "first_publish_year": 2001
  }'
```

## Error Responses

### Validation Error (400):
```json
{
  "title": "Title is required",
  "author": "Author is required"
}
```

### Not Found (404):
```json
{
  "error": "Book not found with id: 999"
}
```

### Conflict (409):
```json
{
  "error": "Book is not available for borrowing"
}
```

## Database
- **Type**: SQLite
- **File**: `library.db` (created automatically)
- **Sample Data**: 4 books are automatically loaded on startup
