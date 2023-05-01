package com.example.springredditclone.exception;

public class PostNotFoundException extends Exception {
    private static final String message = "Post not found";

    public PostNotFoundException() {
        super(message);
    }
}
