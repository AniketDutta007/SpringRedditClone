package com.example.springredditclone.controller;

import com.example.springredditclone.dto.VoteResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.handler.ResponseHandler;
import com.example.springredditclone.model.VoteType;
import com.example.springredditclone.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/post/{post_id}/vote")
@AllArgsConstructor
public class VoteController {
    private final VoteService voteService;
    private final ResponseHandler responseHandler;

    @GetMapping(path = "/upvote")
    public ResponseEntity<Object> upvote(@PathVariable(name = "post_id") Long postId) throws UserNotFoundException, PostNotFoundException {
        voteService.vote(postId, VoteType.UPVOTE);
        return responseHandler.responseBuilder(true, "Upvoted post successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/downvote")
    public ResponseEntity<Object> downvote(@PathVariable(name = "post_id") Long postId) throws UserNotFoundException, PostNotFoundException {
        voteService.vote(postId, VoteType.DOWNVOTE);
        return responseHandler.responseBuilder(true, "Downvoted post successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/unvote")
    public ResponseEntity<Object> unvote(@PathVariable(name = "post_id") Long postId) throws UserNotFoundException, PostNotFoundException {
        voteService.unvote(postId);
        return responseHandler.responseBuilder(true, "Unvoted post successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Object> getAll(@PathVariable(name = "post_id") Long postId) throws PostNotFoundException {
        List<VoteResponse> voteResponses = voteService.getAll(postId);
        return responseHandler.responseBuilder(true, "Fetched votes for post successfully", HttpStatus.OK, voteResponses);
    }
}
