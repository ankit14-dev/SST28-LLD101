public class LaundryAddOn implements PricingComponent {

    @Override
    public Money monthlyFee() {
        return Money.of(500);
    }

    @Override
    public Money deposit() {
        return Money.ZERO;
    }
}