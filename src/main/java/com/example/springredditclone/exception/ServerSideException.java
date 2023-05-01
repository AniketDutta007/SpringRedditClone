package com.example.springredditclone.exception;

public class ServerSideException extends Exception {
    private static final String messsage = "Server side error";
    public ServerSideException() {
        super(messsage);
    }
}
