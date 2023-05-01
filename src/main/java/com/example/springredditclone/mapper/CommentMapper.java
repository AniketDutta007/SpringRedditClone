package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.CommentRequest;
import com.example.springredditclone.dto.CommentResponse;
import com.example.springredditclone.model.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostMapper.class, UserMapper.class})
public interface CommentMapper {
    @Mapping(target = "created", source = "created")
    CommentResponse toDTO(Comment comment);

    @InheritInverseConfiguration
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toEntity(CommentRequest commentRequest);
}
