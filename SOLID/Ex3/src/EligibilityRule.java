import java.util.Optional;

public interface EligibilityRule {
    Optional<RuleViolation> validate(StudentProfile student);
}
