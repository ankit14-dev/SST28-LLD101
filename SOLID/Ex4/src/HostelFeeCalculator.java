import java.util.*;

public class HostelFeeCalculator {
    public FeeBreakdown calculate(List<PricingComponent> components) {
        Money monthly = Money.ZERO;
        Money deposit = Money.ZERO;

        for (PricingComponent c : components) {
            monthly = monthly.add(c.monthlyFee());
            deposit = deposit.add(c.deposit());
        }

        return new FeeBreakdown(monthly, deposit);
    }
}
