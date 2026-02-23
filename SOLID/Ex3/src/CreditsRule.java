import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    public Optional<RuleViolation> validate(StudentProfile s) {
        if (s.hasLowCredits()) {
            return Optional.of(new RuleViolation("credits below 20"));
        }
        return Optional.empty();
    }
}
