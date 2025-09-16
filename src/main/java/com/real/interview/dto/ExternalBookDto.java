package com.real.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalBookDto {
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("author_name")
    private List<String> authorNames;
    
    @JsonProperty("isbn")
    private List<String> isbn;
    
    @JsonProperty("first_publish_year")
    private Integer firstPublishYear;
    
    @JsonProperty("number_of_pages_median")
    private Integer numberOfPages;
    
    @JsonProperty("subject")
    private List<String> subjects;
    
    @JsonProperty("key")
    private String key;
    
    // Helper method to get first author name
    public String getFirstAuthor() {
        return authorNames != null && !authorNames.isEmpty() ? authorNames.get(0) : "Unknown Author";
    }
    
    // Helper method to get first ISBN
    public String getFirstIsbn() {
        return isbn != null && !isbn.isEmpty() ? isbn.get(0) : "No ISBN";
    }
}

