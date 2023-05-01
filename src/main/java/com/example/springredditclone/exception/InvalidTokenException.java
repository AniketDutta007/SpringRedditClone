package com.example.springredditclone.exception;

public class InvalidTokenException extends Exception{
    private static final String message = "Invalid token";
    public InvalidTokenException () {
        super(message);
    }
}
