package com.example.springredditclone.exception;

public class InvalidRefreshTokenException extends Exception {
    private static final String message = "Invalid refresh token";

    public InvalidRefreshTokenException() {
        super(message);
    }
}
