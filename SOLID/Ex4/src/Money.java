public class Money {
    public final double amount;
    public static final Money ZERO = new Money(0);

    public Money(double amount) {
        this.amount = round2(amount);
    }

    public static Money of(double amount) {
        return new Money(amount);
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    private static double round2(double x) {
        return Math.round(x * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }
}
