package RateLimiter;

public class ExternalProviderClient {
    public String call(String tenantId, String payload) {
        return "external-response(" + tenantId + "," + payload + ")";
    }
}
