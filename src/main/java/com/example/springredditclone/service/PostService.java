package com.example.springredditclone.service;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.SubredditRepository;
import com.example.springredditclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional
    public PostResponse create(PostRequest postRequest) throws UserNotFoundException, SubredditNotFoundException {
        Post post = postMapper.toEntity(postRequest);
        post.setCreated(Instant.now());
        post.setUser(authService.getCurrentUser());
        Subreddit subreddit = subredditRepository.findById(postRequest.getSubredditId()).orElseThrow(SubredditNotFoundException::new);
        post.setSubreddit(subreddit);
        post = postRepository.save(post);
        return postMapper.toDTO(post);
    }

    @Transactional
    public List<PostResponse> getAll(Long subredditId, Long userId) throws SubredditNotFoundException, UserNotFoundException {
        boolean subreddit = false, user = false;
        if (subredditId!=null) {
            subreddit = true;
            subredditRepository.findById(subredditId).orElseThrow(SubredditNotFoundException::new);
        }
        if (userId!=null) {
            user = true;
            postRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        }
        List<Post> posts = null;
        if (subreddit&&user) {
            posts = postRepository.findByUserIdAndSubredditId(userId, subredditId);
        } else if (subreddit) {
            posts = postRepository.findBySubredditId(subredditId);
        } else if (user) {
            posts = postRepository.findByUserId(userId);
        } else {
            posts = postRepository.findAll();
        }
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse getById(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return postMapper.toDTO(post);
    }
}
