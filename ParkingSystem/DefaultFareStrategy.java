package ParkingSystem;

public class DefaultFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(ParkingTicket ticket) {
        long durationMinutes = ticket.calculateParkingDurationMinutes();
        return (durationMinutes * ticket.getParkingSlot().getHourlyRate()) / 60.0;
    }
}
