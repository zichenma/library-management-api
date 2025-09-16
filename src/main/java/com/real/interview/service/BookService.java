package com.real.interview.service;

import com.real.interview.entity.Book;
import com.real.interview.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    
    private final BookRepository bookRepository;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchByKeyword(keyword);
    }
    
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsAvailableTrue();
    }
    
    public List<Book> getUnavailableBooks() {
        return bookRepository.findByIsAvailableFalse();
    }
    
    public Book createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        return bookRepository.save(book);
    }
    
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        
        // Check if ISBN is being changed and if the new ISBN already exists
        if (!book.getIsbn().equals(bookDetails.getIsbn()) && 
            bookRepository.existsByIsbn(bookDetails.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + bookDetails.getIsbn() + " already exists");
        }
        
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setIsAvailable(bookDetails.getIsAvailable());
        
        return bookRepository.save(book);
    }
    
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
    
    public Book borrowBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        
        if (!book.getIsAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }
        
        book.setIsAvailable(false);
        return bookRepository.save(book);
    }
    
    public Book returnBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        
        book.setIsAvailable(true);
        return bookRepository.save(book);
    }
}
