public class FeeBreakdown {

    private final Money monthly;
    private final Money deposit;

    public FeeBreakdown(Money monthly, Money deposit) {
        this.monthly = monthly;
        this.deposit = deposit;
    }

    public Money getMonthly() {
        return monthly;
    }

    public Money getDeposit() {
        return deposit;
    }

    public Money totalDueNow() {
        return monthly.add(deposit);
    }
}