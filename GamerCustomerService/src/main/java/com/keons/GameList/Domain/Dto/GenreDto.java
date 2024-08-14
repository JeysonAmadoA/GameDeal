package com.keons.GameList.Domain.Dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GenreDto implements Serializable {
    private long id;
    private String genreName;
}
