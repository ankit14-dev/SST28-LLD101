public class ReportPrinter {
    public void print(StudentProfile s, EligibilityResult result) {
        System.out.println("Student: " + s.getName() + " (CGR=" + String.format("%.2f", s.getCgr())
                + ", attendance=" + s.getAttendancePct() + ", credits=" + s.getEarnedCredits()
                + ", flag=" + LegacyFlags.nameOf(s.getDisciplinaryFlag()) + ")");
        if (result.isEligible()) {
            System.out.println("RESULT: ELIGIBLE");
        } else {
            System.out.println("RESULT: NOT ELIGIBLE");
            for (RuleViolation v : result.getViolations()) {
                System.out.println("- " + v.getMessage());
            }
        }
    }
}
