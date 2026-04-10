package com.example.snl;

import java.util.Objects;

public class ConsoleGameEventPublisher implements GameEventPublisher {
    private final java.io.PrintStream out;

    public ConsoleGameEventPublisher(java.io.PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public void onGameStart(Board board, int totalPlayers) {
        out.println("Game started");
        out.println("Board size: " + board.size() + "x" + board.size());
        out.println("Snakes + ladders: " + board.jumps().size());
        out.println("Players: " + totalPlayers);
    }

    @Override
    public void onTurn(String playerName, int before, int roll, int tentative, int after, JumpType jumpType, boolean blocked) {
        StringBuilder message = new StringBuilder();
        message.append(playerName)
            .append(" rolled ")
            .append(roll)
            .append(" -> ")
            .append(before)
            .append(" to ")
            .append(after);

        if (tentative > after && jumpType == JumpType.SNAKE) {
            message.append(" (snake: ").append(tentative).append(" -> ").append(after).append(")");
        } else if (tentative < after && jumpType == JumpType.LADDER) {
            message.append(" (ladder: ").append(tentative).append(" -> ").append(after).append(")");
        } else if (blocked) {
            message.append(" (move blocked beyond board)");
        }

        out.println(message);
    }

    @Override
    public void onPlayerWon(String playerName, int rank) {
        out.println(playerName + " finished at rank #" + rank);
    }

    @Override
    public void onGameEnd() {
        out.println("Game ended because fewer than 2 players are left in contention.");
    }
}
