import java.util.Random;

public class FakeBookingRepo implements BookingRepository {
    @Override
    public void save(BookingRequest request) {
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        System.out.println("Saved booking: " + bookingId);

    }
}
