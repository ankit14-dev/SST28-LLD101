public class TripleRoomPricing implements PricingComponent {
    @Override
    public Money monthlyFee() {
        return Money.of(12000);
    }
    @Override
    public Money deposit() {
        return Money.of(5000);
    }
}