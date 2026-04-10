package ParkingSystem;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot parkingSlot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSlot parkingSlot, LocalDateTime entryTime) {
        this.ticketId = Objects.requireNonNull(ticketId, "ticketId");
        this.vehicle = Objects.requireNonNull(vehicle, "vehicle");
        this.parkingSlot = Objects.requireNonNull(parkingSlot, "parkingSlot");
        this.entryTime = Objects.requireNonNull(entryTime, "entryTime");
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public SlotType getAllocatedSlotType() {
        return parkingSlot.getSlotType();
    }

    public long calculateParkingDurationMinutes() {
        if (exitTime == null) {
            throw new IllegalStateException("Vehicle has not exited yet");
        }
        return Duration.between(entryTime, exitTime).toMinutes();
    }

    public long closeAt(LocalDateTime exitTime) {
        this.exitTime = Objects.requireNonNull(exitTime, "exitTime");
        return calculateParkingDurationMinutes();
    }
}
