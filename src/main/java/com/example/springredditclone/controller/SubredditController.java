package com.example.springredditclone.controller;

import com.example.springredditclone.dto.SubredditRequest;
import com.example.springredditclone.dto.SubredditResponse;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.handler.ResponseHandler;
import com.example.springredditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/subreddit")
@AllArgsConstructor
public class SubredditController {
    private final SubredditService subredditService;
    private final ResponseHandler responseHandler;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody SubredditRequest subredditRequest) throws UserNotFoundException {
        SubredditResponse subredditResponse = subredditService.create(subredditRequest);
        return responseHandler.responseBuilder(true, "Subreddit created successfully", HttpStatus.CREATED, subredditResponse);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Object> getAll() {
        List<SubredditResponse> subredditResponses = subredditService.getAll();
        return responseHandler.responseBuilder(true, "Subreddits fetched successfully", HttpStatus.OK, subredditResponses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getSubredditById(@PathVariable Long id) throws SubredditNotFoundException {
        SubredditResponse subredditResponse = subredditService.getSubredditById(id);
        return responseHandler.responseBuilder(true, "Subreddit fetched successfully", HttpStatus.OK, subredditResponse);
    }
}
