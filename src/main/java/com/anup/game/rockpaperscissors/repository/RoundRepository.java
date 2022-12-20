package com.anup.game.rockpaperscissors.repository;

import com.anup.game.rockpaperscissors.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
}
