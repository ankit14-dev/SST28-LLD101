package ParkingSystem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingLot {
    private final FareCalculator fareCalculator;
    private final ParkingManager parkingManager;
    private final Map<String, ParkingTicket> activeTickets;
    private final AtomicLong ticketSequence;

    public ParkingLot(FareCalculator fareCalculator, ParkingManager parkingManager) {
        this.fareCalculator = Objects.requireNonNull(fareCalculator, "fareCalculator");
        this.parkingManager = Objects.requireNonNull(parkingManager, "parkingManager");
        this.activeTickets = new HashMap<>();
        this.ticketSequence = new AtomicLong(1);
    }

    public static ParkingLot create(List<ParkingSlot> slots) {
        FareCalculator calculator = new FareCalculator(new DefaultFareStrategy());
        ParkingManager manager = new ParkingManager(slots);
        return new ParkingLot(calculator, manager);
    }

    public ParkingTicket park(Vehicle vehicleDetails, LocalDateTime entryTime, SlotType requestedSlotType, String entryGateID) {
        Objects.requireNonNull(vehicleDetails, "vehicleDetails");
        Objects.requireNonNull(entryTime, "entryTime");
        Objects.requireNonNull(requestedSlotType, "requestedSlotType");
        Objects.requireNonNull(entryGateID, "entryGateID");

        ParkingSlot slot = parkingManager.park(vehicleDetails, requestedSlotType, entryGateID);
        if (slot == null) {
            throw new IllegalStateException("No compatible slot available");
        }

        ParkingTicket ticket = new ParkingTicket(generateTicketId(), vehicleDetails, slot, entryTime);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;

    }

    private String generateTicketId() {
        return "TICKET-" + ticketSequence.getAndIncrement();
    }

    public Map<SlotType, Integer> status() {
        return parkingManager.availableSlotsByType();
    }

    public double exit(ParkingTicket parkingTicket, LocalDateTime exitTime) {
        Objects.requireNonNull(parkingTicket, "parkingTicket");
        Objects.requireNonNull(exitTime, "exitTime");

        ParkingTicket activeTicket = activeTickets.get(parkingTicket.getTicketId());
        if (activeTicket == null) {
            throw new IllegalArgumentException("Unknown or already closed ticket: " + parkingTicket.getTicketId());
        }

        activeTicket.closeAt(exitTime);
        double amount = fareCalculator.calculateFare(activeTicket);
        parkingManager.vacateSlot(activeTicket.getParkingSlot());
        activeTickets.remove(activeTicket.getTicketId());
        return amount;
    }
}
