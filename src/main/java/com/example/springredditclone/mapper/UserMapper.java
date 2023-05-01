package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.UserResponse;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NameMapper.class})
public interface UserMapper {
    @Mapping(target = "noOfPosts", expression = "java(mapPosts(user.getPosts()))")
    @Mapping(target = "created", source = "created")
    UserResponse toDTO(User user);
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "subreddits", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "refreshTokens", ignore = true)
    User toEntity(UserResponse userResponse);

    default Integer mapPosts(List<Post> posts) {
        if (posts==null)
            return 0;
        return posts.size();
    }
}
