package com.real.interview.repository;

import com.real.interview.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    List<Book> findByIsAvailableTrue();
    
    List<Book> findByIsAvailableFalse();
    
    @Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByKeyword(@Param("keyword") String keyword);
    
    boolean existsByIsbn(String isbn);
}
