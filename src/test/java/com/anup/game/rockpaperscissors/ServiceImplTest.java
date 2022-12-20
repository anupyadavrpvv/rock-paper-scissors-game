package com.anup.game.rockpaperscissors;

import com.anup.game.rockpaperscissors.enums.Choice;
import com.anup.game.rockpaperscissors.enums.GameStatus;
import com.anup.game.rockpaperscissors.exception.GameNotFoundException;
import com.anup.game.rockpaperscissors.exception.GameOverException;
import com.anup.game.rockpaperscissors.model.Game;
import com.anup.game.rockpaperscissors.repository.GameRepository;
import com.anup.game.rockpaperscissors.repository.RoundRepository;
import com.anup.game.rockpaperscissors.service.GameService;
import com.anup.game.rockpaperscissors.service.impl.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceImplTest {

    private GameRepository gameRepository;

    private RoundRepository roundRepository;

    private GameService gameService;

    @Before
    public void setup() {
        gameRepository = mock(GameRepository.class);
        roundRepository = mock(RoundRepository.class);
        gameService = new GameServiceImpl(gameRepository, roundRepository);
    }


    @Test(expected = GameNotFoundException.class)
    public void shouldThrowBadGameNotFoundException() throws GameNotFoundException {

        // given
        when(gameRepository.findById(any())).thenReturn(Optional.empty());

        // when
        gameService.getStatus(1L);
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowBadGameOverException() throws GameOverException, GameNotFoundException {

        // given
        Game givenGame = new Game();
        givenGame.setGameStatus(GameStatus.FINISHED);
        Long givenId = new Long(1L);
        when(gameRepository.findById(any())).thenReturn(Optional.ofNullable(givenGame));

        // when
        gameService.play(givenId, Choice.getRandom(), Choice.getRandom());

    }
}
