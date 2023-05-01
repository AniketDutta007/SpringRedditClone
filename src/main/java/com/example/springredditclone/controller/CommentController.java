package com.example.springredditclone.controller;

import com.example.springredditclone.dto.CommentRequest;
import com.example.springredditclone.dto.CommentResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.handler.ResponseHandler;
import com.example.springredditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/post/{post_id}/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ResponseHandler responseHandler;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@PathVariable(name = "post_id") Long postId, @RequestBody CommentRequest commentRequest) throws UserNotFoundException, PostNotFoundException {
        CommentResponse commentResponse = commentService.create(postId, commentRequest);
        return responseHandler.responseBuilder(true, "Comment created successfully", HttpStatus.CREATED, commentResponse);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Object> getByPost(@PathVariable(name = "post_id") Long postId, @RequestParam(name = "user_id", required = false) Long userId) throws PostNotFoundException, UserNotFoundException {
        List<CommentResponse> commentResponses = commentService.getByPostId(postId, userId);
        return responseHandler.responseBuilder(true, "Comments fetched successfully", HttpStatus.OK, commentResponses);
    }
}
