package RateLimiter;

public interface RateLimiter {
    RateLimitDecision allow(String key);
}
