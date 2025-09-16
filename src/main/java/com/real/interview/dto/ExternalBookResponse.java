package com.real.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalBookResponse {
    
    @JsonProperty("docs")
    private List<ExternalBookDto> books;
    
    @JsonProperty("numFound")
    private Integer totalFound;
    
    @JsonProperty("start")
    private Integer start;
}

