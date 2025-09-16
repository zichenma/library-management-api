package com.real.interview.config;

import com.real.interview.entity.Book;
import com.real.interview.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final BookRepository bookRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            // Add some sample books
            Book book1 = new Book();
            book1.setTitle("The Great Gatsby");
            book1.setAuthor("F. Scott Fitzgerald");
            book1.setIsbn("978-0-7432-7356-5");
            book1.setPublicationYear(1925);
            book1.setIsAvailable(true);
            bookRepository.save(book1);
            
            Book book2 = new Book();
            book2.setTitle("To Kill a Mockingbird");
            book2.setAuthor("Harper Lee");
            book2.setIsbn("978-0-06-112008-4");
            book2.setPublicationYear(1960);
            book2.setIsAvailable(true);
            bookRepository.save(book2);
            
            Book book3 = new Book();
            book3.setTitle("1984");
            book3.setAuthor("George Orwell");
            book3.setIsbn("978-0-452-28423-4");
            book3.setPublicationYear(1949);
            book3.setIsAvailable(false);
            bookRepository.save(book3);
            
            Book book4 = new Book();
            book4.setTitle("Pride and Prejudice");
            book4.setAuthor("Jane Austen");
            book4.setIsbn("978-0-14-143951-8");
            book4.setPublicationYear(1813);
            book4.setIsAvailable(true);
            bookRepository.save(book4);
            
            System.out.println("Sample data initialized with " + bookRepository.count() + " books");
        }
    }
}
