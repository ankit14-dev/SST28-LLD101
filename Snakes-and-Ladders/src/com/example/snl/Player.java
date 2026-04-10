package com.example.snl;

import java.util.Objects;

public class Player {
    private final String name;
    private int position;
    private boolean won;

    public Player(String name) {
        this.name = Objects.requireNonNull(name, "name");
        this.position = 0;
        this.won = false;
    }

    public String name() {
        return name;
    }

    public int position() {
        return position;
    }

    public void moveTo(int newPosition) {
        this.position = newPosition;
    }

    public boolean hasWon() {
        return won;
    }

    public void markWon() {
        this.won = true;
    }
}
