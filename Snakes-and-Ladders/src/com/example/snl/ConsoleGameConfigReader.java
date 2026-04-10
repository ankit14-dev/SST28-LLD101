package com.example.snl;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleGameConfigReader implements GameConfigReader {
    private final Scanner scanner;

    public ConsoleGameConfigReader(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public GameConfig readConfig() {
        System.out.print("Enter board size n (board is n x n): ");
        int boardSize = readPositiveInt();

        System.out.print("Enter number of players: ");
        int players = readPositiveInt();

        System.out.print("Enter difficulty_level (easy/hard): ");
        String difficultyInput = scanner.next();
        DifficultyLevel difficulty = DifficultyLevel.fromInput(difficultyInput);

        return new GameConfig(boardSize, players, difficulty);
    }

    private int readPositiveInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a positive integer: ");
        }
        int value = scanner.nextInt();
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive");
        }
        return value;
    }
}
