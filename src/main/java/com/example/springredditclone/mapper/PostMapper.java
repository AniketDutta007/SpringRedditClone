package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Vote;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.example.springredditclone.model.VoteType.UPVOTE;

@Mapper(componentModel = "spring", uses = {SubredditMapper.class, UserMapper.class})
public interface PostMapper {
    @Mapping(target = "votesCount", expression = "java(countVotes(post.getVotes()))")
    @Mapping(target = "commentsCount", expression = "java(count(post.getComments()))")
    @Mapping(target = "created", source = "created")
    PostResponse toDTO(Post post);

    @InheritInverseConfiguration
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "subreddit", ignore = true)
    Post toEntity(PostRequest post);

    default Integer countVotes(List<Vote> votes) {
        if (votes==null)
            return 0;
        Integer count = 0;
        for (Vote vote : votes) {
            count+= UPVOTE.equals(vote.getVoteType())?1:-1;
        }
        return count;
    }
    default Integer count(List list) {
        if (list==null)
            return 0;
        return list.size();
    }
}
