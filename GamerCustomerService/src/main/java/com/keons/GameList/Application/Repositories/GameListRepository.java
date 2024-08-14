package com.keons.GameList.Application.Repositories;

import java.util.Map;

public interface GameListRepository{
    Map<String, Object> findAll();
    Map<String, Object> findById(long id);
}
