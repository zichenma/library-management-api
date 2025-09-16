-- Sample data for Library Management System
-- This SQL script populates the books table with the same data as DataInitializer.java

-- First, let's create the table structure (in case it doesn't exist)
CREATE TABLE IF NOT EXISTS books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    publication_year INTEGER NOT NULL,
    is_available BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO books (title, author, isbn, publication_year, is_available, created_at, updated_at) 
VALUES (
    'The Great Gatsby',
    'F. Scott Fitzgerald',
    '978-0-7432-7356-5',
    1925,
    1,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO books (title, author, isbn, publication_year, is_available, created_at, updated_at) 
VALUES (
    'To Kill a Mockingbird',
    'Harper Lee',
    '978-0-06-112008-4',
    1960,
    1,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO books (title, author, isbn, publication_year, is_available, created_at, updated_at) 
VALUES (
    '1984',
    'George Orwell',
    '978-0-452-28423-4',
    1949,
    0,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO books (title, author, isbn, publication_year, is_available, created_at, updated_at) 
VALUES (
    'Pride and Prejudice',
    'Jane Austen',
    '978-0-14-143951-8',
    1813,
    1,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Verify the data was inserted
SELECT COUNT(*) as total_books FROM books;

-- Show all inserted books
SELECT 
    id,
    title,
    author,
    isbn,
    publication_year,
    CASE 
        WHEN is_available = 1 THEN 'Available'
        ELSE 'Borrowed'
    END as status,
    created_at
FROM books
ORDER BY id;

