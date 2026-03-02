import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repository, OnboardingPrinter printer) {
        this.repository = repository;
        this.printer = printer;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        StudentInputParser parser = new StudentInputParser();
        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        StudentValidator validator = new StudentValidator();
        List<String> errors = validator.validate(name, email, phone, program);
        printer.printErrors(errors);

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repository.save(rec);

        printer.printSuccess(rec, repository.count());
    }
}
