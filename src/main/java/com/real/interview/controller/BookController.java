package com.real.interview.controller;

import com.real.interview.dto.ExternalBookDto;
import com.real.interview.entity.Book;
import com.real.interview.service.BookService;
import com.real.interview.service.ExternalBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {
    
    private final BookService bookService;
    private final ExternalBookService externalBookService;
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return ResponseEntity.ok(books);
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // External API endpoint - Search books from Open Library API
    @GetMapping("/external/search")
    public ResponseEntity<List<ExternalBookDto>> searchExternalBooks(@RequestParam String title) {
        try {
            List<ExternalBookDto> books = externalBookService.searchBooksByTitle(title);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
    
    // Import external book to local database
    @PostMapping("/external/import")
    public ResponseEntity<Book> importExternalBook(@RequestBody ExternalBookDto externalBook) {
        try {
            Book book = new Book();
            book.setTitle(externalBook.getTitle());
            book.setAuthor(externalBook.getFirstAuthor());
            book.setIsbn(externalBook.getFirstIsbn());
            book.setPublicationYear(externalBook.getFirstPublishYear());
            book.setIsAvailable(true);
            
            Book createdBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
