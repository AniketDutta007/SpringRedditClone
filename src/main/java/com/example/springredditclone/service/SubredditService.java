package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubredditRequest;
import com.example.springredditclone.dto.SubredditResponse;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.mapper.SubredditMapper;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.SubredditRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final AuthService authService;

    @Transactional
    public SubredditResponse create(SubredditRequest subredditRequest) throws UserNotFoundException {
        User user = authService.getCurrentUser();
        Subreddit subreddit = subredditMapper.toEntity(subredditRequest);
        subreddit.setUser(user);
        subreddit.setCreated(Instant.now());
        subreddit = subredditRepository.save(subreddit);
        return subredditMapper.toDTO(subreddit);
    }

    @Transactional
    public List<SubredditResponse> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubredditResponse getSubredditById(Long id) throws SubredditNotFoundException {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(SubredditNotFoundException::new);
        return subredditMapper.toDTO(subreddit);
    }
}
