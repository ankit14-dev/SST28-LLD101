package RateLimiter;

import java.util.List;

public interface RateLimitingAlgorithm {
    RateLimitDecision allow(String key, long nowMillis, List<RateLimitRule> rules);
}
