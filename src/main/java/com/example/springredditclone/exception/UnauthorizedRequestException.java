package com.example.springredditclone.exception;

public class UnauthorizedRequestException extends RuntimeException {
    private final static String message = "Unauthorized request";

    public UnauthorizedRequestException() {
        super(message);
    }
}
