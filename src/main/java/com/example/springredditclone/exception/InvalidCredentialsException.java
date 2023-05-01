package com.example.springredditclone.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InvalidCredentialsException extends UsernameNotFoundException {
    private static final String message = "Invalid Credentials";
    public InvalidCredentialsException() {
        super(message);
    }
}
