package com.example.springredditclone.service;

import com.example.springredditclone.dto.VoteResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.mapper.VoteMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.Vote;
import com.example.springredditclone.model.VoteType;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.VoteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteMapper voteMapper;
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(Long postId, VoteType voteType) throws PostNotFoundException, UserNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        User user = authService.getCurrentUser();
        Vote vote = voteRepository.findByPostIdAndUserId(postId, user.getId());
        if (vote==null) {
            vote = new Vote();
            vote.setPost(post);
            vote.setUser(user);
        }
        vote.setVoteType(voteType);
        voteRepository.save(vote);
    }

    @Transactional
    public void unvote(Long postId) throws PostNotFoundException, UserNotFoundException {
        postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        User user = authService.getCurrentUser();
        Vote vote = voteRepository.findByPostIdAndUserId(postId, user.getId());
        if (vote!=null) {
            voteRepository.deleteById(vote.getId());
        }
    }

    @Transactional
    public List<VoteResponse> getAll(Long postId) throws PostNotFoundException {
        postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        List<Vote> votes = voteRepository.findByPostId(postId);
        return votes.stream()
                .map(voteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
