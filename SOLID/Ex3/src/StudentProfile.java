public class StudentProfile {
    private final String rollNo;
    private final String name;
    private final double cgr;
    private final int attendancePct;
    private final int earnedCredits;
    private final int disciplinaryFlag;

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getCgr() {
        return cgr;
    }

    public int getAttendancePct() {
        return attendancePct;
    }

    public int getEarnedCredits() {
        return earnedCredits;
    }

    public int getDisciplinaryFlag() {
        return disciplinaryFlag;
    }

    private static final RuleInput RuleInput = new RuleInput();

    public StudentProfile(String rollNo, String name, double cgr, int attendancePct, int earnedCredits,
            int disciplinaryFlag) {
        this.rollNo = rollNo;
        this.name = name;
        this.cgr = cgr;
        this.attendancePct = attendancePct;
        this.earnedCredits = earnedCredits;
        this.disciplinaryFlag = disciplinaryFlag;
    }

    public boolean hasDisciplinaryIssue() {
        return disciplinaryFlag != LegacyFlags.NONE;
    }

    public boolean hasLowAttendance() {
        return attendancePct < RuleInput.minAttendance;
    }

    public boolean hasLowCGR() {
        return cgr < RuleInput.minCgr;
    }

    public boolean hasLowCredits() {
        return earnedCredits < RuleInput.minCredits;
    }
}
