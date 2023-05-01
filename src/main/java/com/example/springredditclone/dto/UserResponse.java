package com.example.springredditclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @NotBlank(message = "User Id can't be blank")
    private Long id;
    @NotBlank(message = "Name can't be blank")
    private NameDTO name;
    @NotBlank(message = "Username can't be blank")
    private String username;
    private Integer noOfPosts;
    private Instant created;
}
