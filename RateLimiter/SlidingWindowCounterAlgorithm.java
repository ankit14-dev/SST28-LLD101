package RateLimiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterAlgorithm implements RateLimitingAlgorithm {
    private final ConcurrentHashMap<String, KeyState> stateByKey = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, long nowMillis, List<RateLimitRule> rules) {
        KeyState keyState = stateByKey.computeIfAbsent(key, ignored -> new KeyState());

        synchronized (keyState) {
            for (RateLimitRule rule : rules) {
                SlidingState state = keyState.slidingWindows.computeIfAbsent(rule, ignored -> {
                    long window = rule.getWindow().toMillis();
                    long currentWindowStart = (nowMillis / window) * window;
                    return new SlidingState(currentWindowStart, 0, 0);
                });

                normalize(rule, state, nowMillis);

                long windowMs = rule.getWindow().toMillis();
                long elapsedInCurrentWindow = nowMillis - state.currentWindowStart;
                double overlap = (double) (windowMs - elapsedInCurrentWindow) / windowMs;
                double estimatedCount = state.currentCount + (state.previousCount * overlap);

                if (estimatedCount >= rule.getMaxRequests()) {
                    return RateLimitDecision.denied(rule.getName());
                }
            }

            for (RateLimitRule rule : rules) {
                SlidingState state = keyState.slidingWindows.get(rule);
                state.currentCount++;
            }
            return RateLimitDecision.allowed();
        }
    }

    private void normalize(RateLimitRule rule, SlidingState state, long nowMillis) {
        long windowMs = rule.getWindow().toMillis();
        long currentWindowStart = (nowMillis / windowMs) * windowMs;

        if (currentWindowStart == state.currentWindowStart) {
            return;
        }

        long windowsPassed = (currentWindowStart - state.currentWindowStart) / windowMs;
        if (windowsPassed == 1) {
            state.previousCount = state.currentCount;
        } else {
            state.previousCount = 0;
        }
        state.currentCount = 0;
        state.currentWindowStart = currentWindowStart;
    }

    private static final class KeyState {
        private final Map<RateLimitRule, SlidingState> slidingWindows = new HashMap<>();
    }

    private static final class SlidingState {
        private long currentWindowStart;
        private int currentCount;
        private int previousCount;

        private SlidingState(long currentWindowStart, int currentCount, int previousCount) {
            this.currentWindowStart = currentWindowStart;
            this.currentCount = currentCount;
            this.previousCount = previousCount;
        }
    }
}
