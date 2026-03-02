import java.util.List;

public class OnboardingPrinter {

    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printErrors(List<String> errors) {
        if (errors.isEmpty()) return;
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }

    public void printSuccess(StudentRecord rec, int total) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + total);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}