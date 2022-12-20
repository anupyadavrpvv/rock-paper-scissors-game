package com.anup.game.rockpaperscissors.controller;

import com.anup.game.rockpaperscissors.config.Constants;
import com.anup.game.rockpaperscissors.enums.Choice;
import com.anup.game.rockpaperscissors.exception.GameNotFoundException;
import com.anup.game.rockpaperscissors.exception.GameOverException;
import com.anup.game.rockpaperscissors.model.Game;
import com.anup.game.rockpaperscissors.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game start(
            @RequestParam("playerOneName") String playerOneName) {
        return gameService.start(playerOneName, Constants.PLAYER_TWO_NAME);
    }

    @GetMapping("/{gameId}")
    public Game getStatus(
            @PathVariable("gameId") Long id) throws GameNotFoundException {
        return gameService.getStatus(id);
    }

    @PutMapping("/{gameId}")
    public Game play(
            @PathVariable("gameId") Long id,
            @RequestParam("choice") Choice playerOneChoice) throws GameOverException, GameNotFoundException {
        Choice playerTwoChoice = Choice.getRandom();
        return gameService.play(id, playerOneChoice, playerTwoChoice);
    }

}
