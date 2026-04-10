package RateLimiter;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<RateLimitRule> rules = Arrays.asList(
            new RateLimitRule("5-per-minute", 5, Duration.ofMinutes(1)),
            new RateLimitRule("100-per-hour", 100, Duration.ofHours(1))
        );

        ExternalProviderClient client = new ExternalProviderClient();

        InternalService fixedWindowService = new InternalService(
            new PluggableRateLimiter(new FixedWindowCounterAlgorithm(), new SystemTimeProvider(), rules),
            client
        );

        InternalService slidingWindowService = new InternalService(
            new PluggableRateLimiter(new SlidingWindowCounterAlgorithm(), new SystemTimeProvider(), rules),
            client
        );

        String tenant = "T1";

        System.out.println("Fixed Window demo:");
        runBurst(fixedWindowService, tenant);

        System.out.println("Sliding Window demo:");
        runBurst(slidingWindowService, tenant);

        System.out.println("No external call path (no rate-limit check): " + fixedWindowService.processRequest(tenant, "local-only", false));
    }

    private static void runBurst(InternalService service, String tenant) {
        for (int i = 1; i <= 7; i++) {
            String result = service.processRequest(tenant, "p" + i, true);
            System.out.println("Request " + i + " => " + result);
        }
    }
}
