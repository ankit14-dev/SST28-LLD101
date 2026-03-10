public class DriverAllocator implements DriverAllocatorService {
    @Override
    public String allocate(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
