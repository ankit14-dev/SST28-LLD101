import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    public Optional<RuleViolation> validate(StudentProfile s) {
        if (s.hasLowAttendance())
            return Optional.of(new RuleViolation("attendance below 75"));
        return Optional.empty();
    }
}