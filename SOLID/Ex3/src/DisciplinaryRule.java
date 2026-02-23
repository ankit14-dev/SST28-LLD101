import java.util.Optional;

public class DisciplinaryRule implements EligibilityRule {

    @Override
    public Optional<RuleViolation> validate(StudentProfile s) {
        if (s.hasDisciplinaryIssue()) {
            return Optional.of(new RuleViolation("disciplinary flag present"));
        }
        return Optional.empty();
    }
    
}
