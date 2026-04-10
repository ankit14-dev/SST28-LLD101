package com.example.snl;

import java.util.Objects;

public class GameConfig {
    private final int boardSize;
    private final int numberOfPlayers;
    private final DifficultyLevel difficultyLevel;

    public GameConfig(int boardSize, int numberOfPlayers, DifficultyLevel difficultyLevel) {
        if (boardSize < 2) {
            throw new IllegalArgumentException("Board size must be at least 2");
        }
        if (numberOfPlayers < 2) {
            throw new IllegalArgumentException("At least 2 players are required");
        }
        this.boardSize = boardSize;
        this.numberOfPlayers = numberOfPlayers;
        this.difficultyLevel = Objects.requireNonNull(difficultyLevel, "difficultyLevel");
    }

    public int boardSize() {
        return boardSize;
    }

    public int numberOfPlayers() {
        return numberOfPlayers;
    }

    public DifficultyLevel difficultyLevel() {
        return difficultyLevel;
    }
}
