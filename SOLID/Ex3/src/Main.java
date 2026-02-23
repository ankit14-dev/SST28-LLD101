import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        List<EligibilityRule> rules = List.of(
                new DisciplinaryRule(),
                new CGRRule(),
                new AttendanceRule(),
                new CreditsRule());

        EligibilityEvaluator evaluator = new EligibilityEvaluator(rules);
        EligibilityResultRepository repository = new FakeEligibilityStore();
        ReportPrinter printer = new ReportPrinter();

        EligibilityResult result = evaluator.evaluate(s);
        printer.print(s, result);
        repository.save(s.getRollNo(), result.isEligible());
    }
}
