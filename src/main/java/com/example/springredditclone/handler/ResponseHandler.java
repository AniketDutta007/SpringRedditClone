package com.example.springredditclone.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseHandler {

    public ResponseEntity<Object> responseBuilder(boolean success, String message, HttpStatus httpStatus, Map<String, Object> responseMap){
        Map<String, Object> res = new HashMap<>();
        res.put("success", success);
        res.put("data", responseMap);
        res.put("message", message);
        return new ResponseEntity<>(res, httpStatus);
    }
    public ResponseEntity<Object> responseBuilder(boolean success, String message, HttpStatus httpStatus, Object data){
        Map<String, Object> res = new HashMap<>();
        res.put("success", success);
        res.put("data", data);
        res.put("message", message);
        return new ResponseEntity<>(res, httpStatus);
    }
    public ResponseEntity<Object> responseBuilder(boolean success, String message, HttpStatus httpStatus, List<Object> data){
        Map<String, Object> res = new HashMap<>();
        res.put("success", success);
        res.put("data", data);
        res.put("message", message);
        return new ResponseEntity<>(res, httpStatus);
    }
    public ResponseEntity<Object> responseBuilder(boolean success, String message, HttpStatus httpStatus){
        Map<String, Object> res = new HashMap<>();
        res.put("success", success);
        res.put("message", message);
        return new ResponseEntity<>(res, httpStatus);
    }
}