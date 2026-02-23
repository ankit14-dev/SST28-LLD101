import java.util.*;

public class EligibilityEvaluator {

    private final List<EligibilityRule> rules;

    public EligibilityEvaluator(List<EligibilityRule> rules) {
        this.rules = rules;
    }

    public EligibilityResult evaluate(StudentProfile s) {
        List<RuleViolation> violations = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            rule.validate(s).ifPresent(violations::add);
        }
        

        return new EligibilityResult(violations);
    }
}

class EligibilityResult {
    private final boolean eligible;
    private final List<RuleViolation> violations;

    public EligibilityResult(List<RuleViolation> violations) {
        this.violations = violations;
        this.eligible = violations.isEmpty();
    }

    public boolean isEligible() {
        return eligible;
    }

    public List<RuleViolation> getViolations() {
        return violations;
    }
}

