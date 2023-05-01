package com.example.springredditclone.exception;

public class SubredditNotFoundException extends Exception {
    private static final String message = "Subreddit not found";
    public SubredditNotFoundException() {
        super(message);
    }
}
