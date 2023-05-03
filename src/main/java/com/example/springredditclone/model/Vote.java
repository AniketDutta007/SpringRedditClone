package com.example.springredditclone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vote")
@Table(name = "vote")
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private VoteType voteType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}
