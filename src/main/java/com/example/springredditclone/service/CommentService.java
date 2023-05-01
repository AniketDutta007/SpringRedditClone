package com.example.springredditclone.service;

import com.example.springredditclone.dto.CommentRequest;
import com.example.springredditclone.dto.CommentResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.mapper.CommentMapper;
import com.example.springredditclone.model.Comment;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.repository.CommentRepository;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public CommentResponse create(Long postId, CommentRequest commentRequest) throws UserNotFoundException, PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment comment = commentMapper.toEntity(commentRequest);
        comment.setCreated(Instant.now());
        comment.setUser(authService.getCurrentUser());
        comment.setPost(post);
        comment = commentRepository.save(comment);
        return commentMapper.toDTO(comment);
    }

    @Transactional
    public List<CommentResponse> getByPostId(Long postId, Long userId) throws PostNotFoundException, UserNotFoundException {
        postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        if (userId!=null) {
            userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        }
        List<Comment> comments;
        if (userId!=null) {
            comments = commentRepository.findByPostIdAndUserId(postId, userId);
        } else {
            comments = commentRepository.findByPostId(postId);
        }
        return comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
