package com.keons.GameList.Domain.Dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TagDto implements Serializable {
    private long id;
    private String tagName;
}
