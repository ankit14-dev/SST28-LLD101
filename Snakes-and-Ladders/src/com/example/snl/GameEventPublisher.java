package com.example.snl;

public interface GameEventPublisher {
    void onGameStart(Board board, int totalPlayers);

    void onTurn(String playerName, int before, int roll, int tentative, int after, JumpType jumpType, boolean blocked);

    void onPlayerWon(String playerName, int rank);

    void onGameEnd();
}
