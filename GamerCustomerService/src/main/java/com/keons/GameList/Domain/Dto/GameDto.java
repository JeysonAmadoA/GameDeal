package com.keons.GameList.Domain.Dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class GameDto implements Serializable {
    private long id;
    private String slug;
    private String name;
    private LocalDate released;
    private String coverUrl;
    private float rating;
    private List<TagDto> tags;
    private List<GenreDto> genres;
}
