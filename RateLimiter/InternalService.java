package RateLimiter;

import java.util.Objects;

public class InternalService {
    private final RateLimiter rateLimiter;
    private final ExternalProviderClient externalProviderClient;

    public InternalService(RateLimiter rateLimiter, ExternalProviderClient externalProviderClient) {
        this.rateLimiter = Objects.requireNonNull(rateLimiter, "rateLimiter");
        this.externalProviderClient = Objects.requireNonNull(externalProviderClient, "externalProviderClient");
    }

    public String processRequest(String tenantId, String payload, boolean externalCallNeeded) {
        if (!externalCallNeeded) {
            return "handled-locally(" + payload + ")";
        }

        RateLimitDecision decision = rateLimiter.allow(tenantId);
        if (!decision.isAllowed()) {
            return "rate-limited(" + decision.getViolatedRule() + ")";
        }

        return externalProviderClient.call(tenantId, payload);
    }
}
