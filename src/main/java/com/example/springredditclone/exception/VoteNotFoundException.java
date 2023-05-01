package com.example.springredditclone.exception;

public class VoteNotFoundException extends Exception {
    private static final String message = "Vote not found";

    public VoteNotFoundException() {
        super(message);
    }
}
