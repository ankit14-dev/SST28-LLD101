public class GymAddOn implements PricingComponent {
    @Override
    public Money monthlyFee() {
        return Money.of(300);
    }

    @Override
    public Money deposit() {
        return Money.ZERO;
    }
    
}
