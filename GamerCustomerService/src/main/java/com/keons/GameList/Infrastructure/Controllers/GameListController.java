package com.keons.GameList.Infrastructure.Controllers;

import com.keons.Core.Infrastructure.Controllers.BaseController;
import com.keons.GameList.Application.Services.GameListService;
import com.keons.GameList.Domain.Dto.GameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/games")
public class GameListController extends BaseController {

    private final GameListService gameListService;

    public GameListController(GameListService gameListService) {
        this.gameListService = gameListService;
    }

    @GetMapping
    public ResponseEntity<?> getAllGames() {
        List<GameDto> gamesList = gameListService.getAll();
        Map<String, Object> response = getJsonResponse(gamesList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable long id) {
        GameDto game = gameListService.filterById(id);
        Map<String, Object> response = getJsonResponse(game);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
