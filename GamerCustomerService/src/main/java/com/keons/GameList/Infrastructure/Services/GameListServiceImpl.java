package com.keons.GameList.Infrastructure.Services;

import com.keons.GameList.Application.Repositories.GameListRepository;
import com.keons.GameList.Application.Services.GameListService;
import com.keons.GameList.Domain.Dto.GameDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

import static com.keons.GameList.Application.Mappers.GameDataMapper.toDto;
import static com.keons.GameList.Application.Mappers.GameDataMapper.toDtoList;

@Service
public class GameListServiceImpl implements GameListService {

    private final GameListRepository gameListRepository;

    public GameListServiceImpl(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }

    @Cacheable("getAllGames")
    @Override
    public List<GameDto> getAll() {
        return toDtoList(gameListRepository.findAll());
    }

    @Override
    public GameDto filterById(long id) {
        return toDto((LinkedHashMap) gameListRepository.findById(id));
    }
}
