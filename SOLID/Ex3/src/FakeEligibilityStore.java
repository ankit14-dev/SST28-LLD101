public class FakeEligibilityStore implements EligibilityResultRepository {

    @Override
    public void save(String rollNo, boolean eligible) {
        System.out.println("Saved evaluation for roll=" + rollNo);
    }
}
