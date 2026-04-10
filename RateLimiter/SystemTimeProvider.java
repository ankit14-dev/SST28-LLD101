package RateLimiter;

public class SystemTimeProvider implements TimeProvider {
    @Override
    public long nowMillis() {
        return System.currentTimeMillis();
    }
}
