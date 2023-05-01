package com.example.springredditclone.dto;

import com.example.springredditclone.model.VoteType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteResponse {
    @NotBlank(message = "Vote type can't be blank")
    private VoteType voteType;
    @NotBlank(message = "User can't be blank")
    private UserResponse user;
}
