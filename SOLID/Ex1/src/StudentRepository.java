import java.util.List;

public interface StudentRepository {
    void save(StudentRecord rec);

    int count();
    List<StudentRecord> all();
}
