package com.example.snl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {
    private final int size;
    private final int lastCell;
    private final Map<Integer, Integer> jumps;
    private final Map<Integer, JumpType> jumpTypes;

    public Board(int size, Map<Integer, Integer> jumps, Map<Integer, JumpType> jumpTypes) {
        if (size < 2) {
            throw new IllegalArgumentException("Board size must be at least 2");
        }
        this.size = size;
        this.lastCell = size * size;
        this.jumps = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(jumps, "jumps")));
        this.jumpTypes = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(jumpTypes, "jumpTypes")));
    }

    public int size() {
        return size;
    }

    public int lastCell() {
        return lastCell;
    }

    public int resolvePosition(int position) {
        Integer destination = jumps.get(position);
        return destination == null ? position : destination;
    }

    public JumpType jumpTypeAt(int position) {
        return jumpTypes.get(position);
    }

    public Map<Integer, Integer> jumps() {
        return jumps;
    }
}
