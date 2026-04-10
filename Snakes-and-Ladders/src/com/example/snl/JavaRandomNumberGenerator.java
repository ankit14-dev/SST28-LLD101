package com.example.snl;

import java.util.Objects;
import java.util.Random;

public class JavaRandomNumberGenerator implements RandomNumberGenerator {
    private final Random random;

    public JavaRandomNumberGenerator(Random random) {
        this.random = Objects.requireNonNull(random, "random");
    }

    @Override
    public int nextInt(int boundExclusive) {
        return random.nextInt(boundExclusive);
    }
}
