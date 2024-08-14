package com.keons.GameList.Application.Mappers;

import com.keons.GameList.Domain.Dto.GameDto;
import com.keons.GameList.Domain.Dto.GenreDto;
import com.keons.GameList.Domain.Dto.TagDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameDataMapper {

    public static List<GameDto> toDtoList(Map<String, Object> data){
        ArrayList<LinkedHashMap<String, Object>> results = (ArrayList<LinkedHashMap<String, Object>>) data.get("results");
        return results.stream().map(GameDataMapper::toDto).collect(Collectors.toList());
    }

    public static GameDto toDto(LinkedHashMap<String, Object> data){
        ArrayList<LinkedHashMap<String, Object>> tagsList = (ArrayList<LinkedHashMap<String, Object>>) data.get("tags");
        List<TagDto> tags = tagsList.stream().map(TagsMapper::toDto).collect(Collectors.toList());

        ArrayList<LinkedHashMap<String, Object>> genreList = (ArrayList<LinkedHashMap<String, Object>>) data.get("genres");
        List<GenreDto> genres = genreList.stream().map(GenreMapper::toDto).collect(Collectors.toList());

        return GameDto.builder()
                .id(((Integer) data.get("id")).longValue())
                .slug((String) data.get("slug"))
                .name((String) data.get("name"))
                .released(LocalDate.parse((String) data.get("released")))
                .rating(((Double) data.get("rating")).floatValue())
                .coverUrl((String) data.get("background_image"))
                .tags(tags)
                .genres(genres)
                .build();
    }
}
