package com.example.springredditclone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subreddit")
@Table(name = "subreddit")
@Builder
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "subreddit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;
    private Instant created;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
