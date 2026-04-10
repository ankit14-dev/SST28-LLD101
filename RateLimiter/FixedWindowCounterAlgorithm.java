package RateLimiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowCounterAlgorithm implements RateLimitingAlgorithm {
    private final ConcurrentHashMap<String, KeyState> stateByKey = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, long nowMillis, List<RateLimitRule> rules) {
        KeyState keyState = stateByKey.computeIfAbsent(key, ignored -> new KeyState());

        synchronized (keyState) {
            for (RateLimitRule rule : rules) {
                long windowMs = rule.getWindow().toMillis();
                long windowStart = (nowMillis / windowMs) * windowMs;

                WindowState ws = keyState.fixedWindows.computeIfAbsent(rule, ignored -> new WindowState(windowStart, 0));
                if (ws.windowStart != windowStart) {
                    ws.windowStart = windowStart;
                    ws.count = 0;
                }

                if (ws.count >= rule.getMaxRequests()) {
                    return RateLimitDecision.denied(rule.getName());
                }
            }

            for (RateLimitRule rule : rules) {
                WindowState ws = keyState.fixedWindows.get(rule);
                ws.count++;
            }
            return RateLimitDecision.allowed();
        }
    }

    private static final class KeyState {
        private final Map<RateLimitRule, WindowState> fixedWindows = new HashMap<>();
    }

    private static final class WindowState {
        private long windowStart;
        private int count;

        private WindowState(long windowStart, int count) {
            this.windowStart = windowStart;
            this.count = count;
        }
    }
}
