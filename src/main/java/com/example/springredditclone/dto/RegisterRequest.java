package com.example.springredditclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private NameDTO name;
    @NotBlank(message = "Username can't be null")
    private String username;
    @NotBlank(message = "Email can't be null")
    private String email;
    @NotBlank(message = "Password can't be null")
    private String password;
}
