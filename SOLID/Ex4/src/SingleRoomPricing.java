public class SingleRoomPricing implements PricingComponent {
    @Override
    public Money monthlyFee() {
        return Money.of(14000);
    }
    @Override
    public Money deposit() {
        return Money.of(5000);
    }
}
