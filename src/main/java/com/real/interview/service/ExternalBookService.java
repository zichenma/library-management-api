package com.real.interview.service;

import com.real.interview.dto.ExternalBookDto;
import com.real.interview.dto.ExternalBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalBookService {
    
    private final RestTemplate restTemplate;
    
    @Value("${external.open-library.api-url}")
    private String openLibraryApiUrl;
    
    public List<ExternalBookDto> searchBooksByTitle(String title) {
        try {
            String url = String.format("%s?q=title:%s&limit=5", openLibraryApiUrl, title);
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
