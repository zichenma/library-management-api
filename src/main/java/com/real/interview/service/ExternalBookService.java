package com.real.interview.service;

import com.real.interview.dto.ExternalBookDto;
import com.real.interview.dto.ExternalBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalBookService {
    
    private final RestTemplate restTemplate;
    
    private static final String OPEN_LIBRARY_API_URL = "https://openlibrary.org/search.json";
    
    public List<ExternalBookDto> searchBooksByTitle(String title) {
        try {
            String url = String.format("%s?q=title:%s&limit=5", OPEN_LIBRARY_API_URL, title);
            ExternalBookResponse response = restTemplate.getForObject(url, ExternalBookResponse.class);
            
            if (response != null && response.getBooks() != null) {
                return response.getBooks();
            }
            return Collections.emptyList();
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch books from external API: " + e.getMessage());
        }
    }
}
