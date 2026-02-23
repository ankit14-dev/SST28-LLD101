public class RuleViolation {
    private final String message;

    public RuleViolation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}