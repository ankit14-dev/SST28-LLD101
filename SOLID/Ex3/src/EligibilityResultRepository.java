public interface EligibilityResultRepository {
    void save(String rollNo, boolean eligible);
}
