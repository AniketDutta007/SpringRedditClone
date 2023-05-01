package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.SubredditRequest;
import com.example.springredditclone.dto.SubredditResponse;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface SubredditMapper {
    @Mapping(target = "noOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    @Mapping(target = "created", source = "created")
    SubredditResponse toDTO(Subreddit subreddit);
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "created", ignore = true)
    Subreddit toEntity(SubredditRequest subredditDTO);

    default Integer mapPosts(List<Post> posts) {
        if (posts==null)
            return 0;
        return posts.size();
    }
}
