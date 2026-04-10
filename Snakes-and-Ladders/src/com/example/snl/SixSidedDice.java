package com.example.snl;

import java.util.Objects;

public class SixSidedDice implements Dice {
    private final RandomNumberGenerator random;

    public SixSidedDice(RandomNumberGenerator random) {
        this.random = Objects.requireNonNull(random, "random");
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
