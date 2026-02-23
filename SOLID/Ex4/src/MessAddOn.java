public class MessAddOn implements PricingComponent {
    @Override
    public Money monthlyFee() {
        return Money.of(1000);
    }

    @Override
    public Money deposit() {
        return Money.ZERO;
    }
}
