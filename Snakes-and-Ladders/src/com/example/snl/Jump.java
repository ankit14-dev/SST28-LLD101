package com.example.snl;

import java.util.Objects;

public final class Jump {
    private final int start;
    private final int end;
    private final JumpType type;

    public Jump(int start, int end, JumpType type) {
        this.start = start;
        this.end = end;
        this.type = Objects.requireNonNull(type, "type");
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public JumpType type() {
        return type;
    }
}
