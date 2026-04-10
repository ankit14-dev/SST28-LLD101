package com.example.snl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameEngine {
    private final Board board;
    private final Dice dice;
    private final GameEventPublisher publisher;

    public GameEngine(Board board, Dice dice, GameEventPublisher publisher) {
        this.board = Objects.requireNonNull(board, "board");
        this.dice = Objects.requireNonNull(dice, "dice");
        this.publisher = Objects.requireNonNull(publisher, "publisher");
    }

    public void play(List<Player> players) {
        Objects.requireNonNull(players, "players");
        if (players.size() < 2) {
            throw new IllegalArgumentException("At least 2 players are required");
        }

        publisher.onGameStart(board, players.size());
        int rank = 0;

        while (playersStillInContention(players) >= 2) {
            for (Player player : players) {
                if (player.hasWon()) {
                    continue;
                }

                int before = player.position();
                int roll = dice.roll();
                int tentative = before;
                int after = before;
                JumpType jumpType = null;
                boolean blocked = false;

                if (before + roll <= board.lastCell()) {
                    tentative = before + roll;
                    jumpType = board.jumpTypeAt(tentative);
                    after = board.resolvePosition(tentative);
                } else {
                    blocked = true;
                }

                player.moveTo(after);
                publisher.onTurn(player.name(), before, roll, tentative, after, jumpType, blocked);

                if (after == board.lastCell() && !player.hasWon()) {
                    player.markWon();
                    rank++;
                    publisher.onPlayerWon(player.name(), rank);
                }

                if (playersStillInContention(players) < 2) {
                    break;
                }
            }
        }

        publisher.onGameEnd();
    }

    private int playersStillInContention(List<Player> players) {
        int count = 0;
        for (Player player : players) {
            if (!player.hasWon()) {
                count++;
            }
        }
        return count;
    }

    public static List<Player> createPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2) {
            throw new IllegalArgumentException("At least 2 players are required");
        }
        List<Player> players = new ArrayList<>(numberOfPlayers);
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player-" + i));
        }
        return players;
    }
}
