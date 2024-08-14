package com.keons.GameList.Application.Mappers;

import com.keons.GameList.Domain.Dto.TagDto;

import java.util.Map;

public class TagsMapper {

    public static TagDto toDto(Map<String, Object> tagData){
        return TagDto.builder()
                .id(((Integer) tagData.get("id")).longValue())
                .tagName((String) tagData.get("name"))
                .build();
    }
}
