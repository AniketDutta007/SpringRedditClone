package com.example.springredditclone.repository;

import com.example.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByPostIdAndUserId(Long postId, Long id);

    List<Vote> findByPostId(Long postId);
}
