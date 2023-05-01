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
public class CommentResponse {
    private Long id;
    @NotBlank(message = "Content can't be blank")
    private String content;
    private Instant created;
    @NotBlank(message = "Post can't be blank")
    private PostResponse post;
    @NotBlank(message = "User can't be blank")
    private UserResponse user;
}
