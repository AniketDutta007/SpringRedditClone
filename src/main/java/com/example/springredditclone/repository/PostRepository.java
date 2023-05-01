package com.example.springredditclone.repository;

import com.example.springredditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdAndSubredditId(Long userId, Long subredditId);
    List<Post> findByUserId(Long userId);
    List<Post> findBySubredditId(Long subredditId);
}
