package com.example.springredditclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditResponse {
    private Long id;
    @NotBlank(message = "Community name is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    private Integer noOfPosts = 0;
    @NotBlank(message = "User is required")
    private UserResponse user;
    private Instant created;
}
