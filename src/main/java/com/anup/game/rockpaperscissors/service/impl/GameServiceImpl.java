package com.anup.game.rockpaperscissors.service.impl;

import com.anup.game.rockpaperscissors.config.Constants;
import com.anup.game.rockpaperscissors.enums.Choice;
import com.anup.game.rockpaperscissors.enums.GameStatus;
import com.anup.game.rockpaperscissors.enums.Result;
import com.anup.game.rockpaperscissors.exception.GameNotFoundException;
import com.anup.game.rockpaperscissors.exception.GameOverException;
import com.anup.game.rockpaperscissors.model.Game;
import com.anup.game.rockpaperscissors.model.Round;
import com.anup.game.rockpaperscissors.repository.GameRepository;
import com.anup.game.rockpaperscissors.repository.RoundRepository;
import com.anup.game.rockpaperscissors.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final RoundRepository roundRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, RoundRepository roundRepository) {
        this.gameRepository = gameRepository;
        this.roundRepository = roundRepository;
    }

    @Override
    public Game start(String playerOneName, String playerTwoName) {
        Game game = createGame(playerOneName, playerTwoName);
        return gameRepository.save(game);
    }

    @Override
    public Game getStatus(Long id) throws GameNotFoundException {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    @Override
    public Game play(Long id, Choice playerOneChoice, Choice playerTwoChoice) throws GameNotFoundException, GameOverException {
        Game game = getStatus(id);
        validateGameStatus(game);
        Round round = createRound(playerOneChoice, playerTwoChoice, game);
        addRound(game, round);
        incrementScore(round, game);
        checkGameWinner(game);
        return gameRepository.save(game);
    }

    private void addRound(Game game, Round round) {
        if (CollectionUtils.isEmpty(game.getRounds())) {
            List<Round> rounds = new ArrayList<>();
            game.setRounds(rounds);
        }
        game.getRounds().add(round);
    }

    private Round createRound(Choice playerOneChoice, Choice playerTwoChoice, Game game) {
        Result result = evaluateChoices(playerOneChoice, playerTwoChoice);
        Round round = new Round(playerOneChoice, playerTwoChoice, result, game);
        return roundRepository.save(round);
    }

    private void checkGameWinner(Game game) {
        if (Constants.WINNING_SCORE.equals(game.getPlayerOneScore()) ||
                Constants.WINNING_SCORE.equals(game.getPlayerTwoScore())) {
            game.setGameStatus(GameStatus.FINISHED);
        }
    }

    private void incrementScore(Round round, Game game) {
        if (round.getPlayerOneResult().equals(Result.WIN)) {
            game.setPlayerOneScore(game.getPlayerOneScore() + 1);
        } else if (round.getPlayerOneResult().equals(Result.LOOSE)) {
            game.setPlayerTwoScore(game.getPlayerTwoScore() + 1);
        }
    }

    private Result evaluateChoices(Choice playerOne, Choice playerTwo) {
        validateChoice(playerOne);
        Result result = Result.DRAW;
        if (playerOne.isBetterThan(playerTwo)) {
            result = Result.WIN;
        } else if (playerTwo.isBetterThan(playerOne)) {
            result = Result.LOOSE;
        }
        return result;
    }

    private Game createGame(String playerOneName, String playerTwoName) {
        Game game = new Game(playerOneName, playerTwoName);
        game.setGameStatus(GameStatus.STARTED);
        return game;
    }

    private void validateChoice(Choice playerOneChoice) {
        if (Objects.isNull(playerOneChoice)) {
            String msg = String.format("Choice cannot be empty, playerOneChoice: {}", playerOneChoice);
            throw new IllegalArgumentException(msg);
        }
    }

    private void validateGameStatus(Game game) throws GameOverException {
        if (!GameStatus.STARTED.equals(game.getGameStatus())) {
            throw new GameOverException("Game is over, please create new game");
        }
    }

}
