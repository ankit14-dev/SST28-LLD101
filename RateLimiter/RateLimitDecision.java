package RateLimiter;

public final class RateLimitDecision {
    private final boolean allowed;
    private final String violatedRule;

    private RateLimitDecision(boolean allowed, String violatedRule) {
        this.allowed = allowed;
        this.violatedRule = violatedRule;
    }

    public static RateLimitDecision allowed() {
        return new RateLimitDecision(true, null);
    }

    public static RateLimitDecision denied(String violatedRule) {
        return new RateLimitDecision(false, violatedRule);
    }

    public boolean isAllowed() {
        return allowed;
    }

    public String getViolatedRule() {
        return violatedRule;
    }
}
