import java.util.Optional;

public class CGRRule implements EligibilityRule {
    public Optional<RuleViolation> validate(StudentProfile s) {
        if (s.hasLowCGR()) {
            return Optional.of(new RuleViolation("CGR below 8.0"));
        }
        return Optional.empty();
    }
}
