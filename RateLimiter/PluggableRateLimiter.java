package RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PluggableRateLimiter implements RateLimiter {
    private final RateLimitingAlgorithm algorithm;
    private final TimeProvider timeProvider;
    private final List<RateLimitRule> rules;

    public PluggableRateLimiter(RateLimitingAlgorithm algorithm, TimeProvider timeProvider, List<RateLimitRule> rules) {
        this.algorithm = Objects.requireNonNull(algorithm, "algorithm");
        this.timeProvider = Objects.requireNonNull(timeProvider, "timeProvider");
        this.rules = new ArrayList<>(Objects.requireNonNull(rules, "rules"));
        if (this.rules.isEmpty()) {
            throw new IllegalArgumentException("At least one rate limit rule is required");
        }
    }

    @Override
    public RateLimitDecision allow(String key) {
        String nonNullKey = Objects.requireNonNull(key, "key");
        return algorithm.allow(nonNullKey, timeProvider.nowMillis(), rules);
    }
}
