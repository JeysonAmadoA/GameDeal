package com.keons.GameList.Application.Services;

import com.keons.GameList.Domain.Dto.GameDto;

import java.util.List;

public interface GameListService {
    List<GameDto> getAll();
    GameDto filterById(long id);
}
