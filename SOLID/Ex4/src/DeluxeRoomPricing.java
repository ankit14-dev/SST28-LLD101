public class DeluxeRoomPricing implements PricingComponent {
    @Override
    public Money monthlyFee() {
        return Money.of(16000);
    }
    @Override
    public Money deposit() {
        return Money.of(5000);
    }
    
}
