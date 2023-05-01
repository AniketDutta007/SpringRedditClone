package com.example.springredditclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditRequest {
    private Long id;
    @NotBlank(message = "Community name is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
}
