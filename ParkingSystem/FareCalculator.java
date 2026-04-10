package ParkingSystem;

import java.util.Objects;

public class FareCalculator {
    private final FareStrategy fareStrategy;

    public FareCalculator(FareStrategy fareStrategy) {
        this.fareStrategy = Objects.requireNonNull(fareStrategy, "fareStrategy");
    }

    public double calculateFare(ParkingTicket ticket) {
        return fareStrategy.calculateFare(ticket);
    }
}
