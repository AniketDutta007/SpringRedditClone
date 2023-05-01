package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.NameDTO;
import com.example.springredditclone.model.Name;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameMapper {
    NameDTO toDTO(Name name);
    Name toEntity(NameDTO nameDTO);
}
