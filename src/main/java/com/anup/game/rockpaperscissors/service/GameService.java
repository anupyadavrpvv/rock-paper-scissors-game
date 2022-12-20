package com.anup.game.rockpaperscissors.service;

import com.anup.game.rockpaperscissors.enums.Choice;
import com.anup.game.rockpaperscissors.exception.GameNotFoundException;
import com.anup.game.rockpaperscissors.exception.GameOverException;
import com.anup.game.rockpaperscissors.model.Game;


public interface GameService {

    Game start(String playerOneName, String playerTwoName);

    Game getStatus(Long id) throws GameNotFoundException;

    Game play(Long id, Choice playerOneChoice, Choice playerTwoChoice) throws GameNotFoundException, GameOverException;
}
