package com.example.springredditclone.exception;

public class UserNotFoundException extends Exception {
    private static final String message = "User not found";
    public UserNotFoundException () {
        super(message);
    }
}
