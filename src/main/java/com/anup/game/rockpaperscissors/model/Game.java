package com.anup.game.rockpaperscissors.model;

import com.anup.game.rockpaperscissors.enums.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private GameStatus gameStatus;

    private String playerOneName;

    private Integer playerOneScore;

    private String playerTwoName;

    private Integer playerTwoScore;

    @OneToMany
    private List<Round> rounds;

    public Game(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.rounds = new ArrayList<>();
    }
}
