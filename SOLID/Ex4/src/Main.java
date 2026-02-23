import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req = new BookingRequest(
                LegacyRoomTypes.DOUBLE,
                List.of(AddOn.LAUNDRY, AddOn.MESS));

        HostelFeeCalculator calculator = new HostelFeeCalculator();
        FakeBookingRepo repo = new FakeBookingRepo();

        List<PricingComponent> components = new ArrayList<>();

        // Room pricing
        components.add(
                PricingFactory.room(req.getRoomType()));

        // Add-on pricing
        for (AddOn addOn : req.getAddOns()) {
            components.add(
                    PricingFactory.addOn(addOn));
        }

        components.add(new LateFeeRule());

        FeeBreakdown fees = calculator.calculate(components);

        ReceiptPrinter.print(req, fees);

        repo.save(req);
    }
}