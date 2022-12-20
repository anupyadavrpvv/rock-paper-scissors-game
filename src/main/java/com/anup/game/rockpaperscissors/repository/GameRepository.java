package com.anup.game.rockpaperscissors.repository;

import com.anup.game.rockpaperscissors.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
