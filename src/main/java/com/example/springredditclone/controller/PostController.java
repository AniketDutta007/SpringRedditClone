package com.example.springredditclone.controller;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.handler.ResponseHandler;
import com.example.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/post")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final ResponseHandler responseHandler;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody PostRequest postRequest) throws UserNotFoundException, SubredditNotFoundException {
        PostResponse postResponse = postService.create(postRequest);
        return responseHandler.responseBuilder(true, "Post created successfully", HttpStatus.CREATED, postResponse);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Object> getAll(@RequestParam(name = "subreddit_id", required = false) Long subredditId, @RequestParam(name = "user_id", required = false) Long userId) throws SubredditNotFoundException, UserNotFoundException {
        System.out.println(subredditId+" "+userId);
        List<PostResponse> postResponses = postService.getAll(subredditId, userId);
        return responseHandler.responseBuilder(true, "Posts fetched successfully", HttpStatus.OK, postResponses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable(name = "id") Long postId) throws PostNotFoundException {
        PostResponse postResponse = postService.getById(postId);
        return responseHandler.responseBuilder(true, "Post fetched successfully", HttpStatus.OK, postResponse);
    }
}
