package RateLimiter;

import java.time.Duration;
import java.util.Objects;

public final class RateLimitRule {
    private final String name;
    private final int maxRequests;
    private final Duration window;

    public RateLimitRule(String name, int maxRequests, Duration window) {
        this.name = Objects.requireNonNull(name, "name");
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("maxRequests must be greater than 0");
        }
        if (window == null || window.isZero() || window.isNegative()) {
            throw new IllegalArgumentException("window must be a positive duration");
        }
        this.maxRequests = maxRequests;
        this.window = window;
    }

    public String getName() {
        return name;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public Duration getWindow() {
        return window;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RateLimitRule)) {
            return false;
        }
        RateLimitRule that = (RateLimitRule) other;
        return this.maxRequests == that.maxRequests
            && this.name.equals(that.name)
            && this.window.equals(that.window);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + maxRequests;
        result = 31 * result + window.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RateLimitRule{" +
            "name='" + name + '\'' +
            ", maxRequests=" + maxRequests +
            ", window=" + window +
            '}';
    }
}
