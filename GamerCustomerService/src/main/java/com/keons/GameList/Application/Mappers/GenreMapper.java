package com.keons.GameList.Application.Mappers;

import com.keons.GameList.Domain.Dto.GenreDto;

import java.util.Map;

public class GenreMapper {

    public static GenreDto toDto(Map<String, Object> tagData){
        return GenreDto.builder()
                .id(((Integer) tagData.get("id")).longValue())
                .genreName((String) tagData.get("name"))
                .build();
    }
}
