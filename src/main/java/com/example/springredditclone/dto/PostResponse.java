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
public class PostResponse {
    private Long id;
    @NotBlank(message = "Title of post can't be blank")
    private String title;
    private String url;
    private String description;
    private Integer votesCount = 0;
    private Integer commentsCount = 0;
    private Instant created;
    @NotBlank(message = "User of post can't be blank")
    private UserResponse user;
    @NotBlank(message = "Subreddit of post can't be blank")
    private SubredditResponse subreddit;
}
