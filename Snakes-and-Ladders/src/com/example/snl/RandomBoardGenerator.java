package com.example.snl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RandomBoardGenerator implements BoardGenerator {
    private final RandomNumberGenerator random;

    public RandomBoardGenerator(RandomNumberGenerator random) {
        this.random = Objects.requireNonNull(random, "random");
    }

    @Override
    public Board generate(int boardSize, DifficultyLevel difficultyLevel) {
        Objects.requireNonNull(difficultyLevel, "difficultyLevel");
        if (boardSize < 2) {
            throw new IllegalArgumentException("board size must be at least 2");
        }

        int lastCell = boardSize * boardSize;
        int jumpCount = boardSize;

        int minSnakeLength = difficultyLevel == DifficultyLevel.HARD ? 3 : 2;
        int minLadderLength = difficultyLevel == DifficultyLevel.HARD ? 2 : 3;

        Map<Integer, Integer> jumps = new HashMap<>();
        Map<Integer, JumpType> jumpTypes = new HashMap<>();
        Set<Integer> occupiedStarts = new HashSet<>();

        placeSnakes(lastCell, jumpCount, minSnakeLength, jumps, jumpTypes, occupiedStarts);
        placeLadders(lastCell, jumpCount, minLadderLength, jumps, jumpTypes, occupiedStarts);

        return new Board(boardSize, jumps, jumpTypes);
    }

    private void placeSnakes(
        int lastCell,
        int snakeCount,
        int minSnakeLength,
        Map<Integer, Integer> jumps,
        Map<Integer, JumpType> jumpTypes,
        Set<Integer> occupiedStarts
    ) {
        int created = 0;
        int attempts = 0;
        int maxAttempts = snakeCount * 600;

        while (created < snakeCount && attempts < maxAttempts) {
            attempts++;
            int head = randomBetween(3, lastCell - 1);
            int maxTail = head - minSnakeLength;
            if (maxTail < 2) {
                continue;
            }
            int tail = randomBetween(2, maxTail);

            if (occupiedStarts.contains(head)) {
                continue;
            }

            if (wouldCreateCycle(head, tail, jumps)) {
                continue;
            }

            jumps.put(head, tail);
            jumpTypes.put(head, JumpType.SNAKE);
            occupiedStarts.add(head);
            created++;
        }

        if (created < snakeCount) {
            throw new IllegalStateException("Unable to place all snakes; try a larger board size");
        }
    }

    private void placeLadders(
        int lastCell,
        int ladderCount,
        int minLadderLength,
        Map<Integer, Integer> jumps,
        Map<Integer, JumpType> jumpTypes,
        Set<Integer> occupiedStarts
    ) {
        int created = 0;
        int attempts = 0;
        int maxAttempts = ladderCount * 600;

        while (created < ladderCount && attempts < maxAttempts) {
            attempts++;
            int start = randomBetween(2, lastCell - 2);
            int minEnd = start + minLadderLength;
            if (minEnd > lastCell - 1) {
                continue;
            }
            int end = randomBetween(minEnd, lastCell - 1);

            if (occupiedStarts.contains(start)) {
                continue;
            }

            if (wouldCreateCycle(start, end, jumps)) {
                continue;
            }

            jumps.put(start, end);
            jumpTypes.put(start, JumpType.LADDER);
            occupiedStarts.add(start);
            created++;
        }

        if (created < ladderCount) {
            throw new IllegalStateException("Unable to place all ladders; try a larger board size");
        }
    }

    private boolean wouldCreateCycle(int start, int end, Map<Integer, Integer> existingJumps) {
        int cursor = end;
        Set<Integer> visited = new HashSet<>();

        while (existingJumps.containsKey(cursor)) {
            if (cursor == start) {
                return true;
            }
            if (!visited.add(cursor)) {
                return true;
            }
            cursor = existingJumps.get(cursor);
        }

        return cursor == start;
    }

    private int randomBetween(int minInclusive, int maxInclusive) {
        if (maxInclusive < minInclusive) {
            throw new IllegalArgumentException("invalid random bounds");
        }
        int bound = maxInclusive - minInclusive + 1;
        return random.nextInt(bound) + minInclusive;
    }
}
