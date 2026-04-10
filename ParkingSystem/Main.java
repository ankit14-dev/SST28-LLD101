package ParkingSystem;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<ParkingSlot> slots = Arrays.asList(
            new SmallSlot("S1", gateDistances(2, 6)),
            new SmallSlot("S2", gateDistances(4, 5)),
            new MediumSlot("M1", gateDistances(3, 2)),
            new MediumSlot("M2", gateDistances(6, 1)),
            new LargeSlot("L1", gateDistances(5, 3)),
            new LargeSlot("L2", gateDistances(8, 2))
        );

        ParkingLot parkingLot = ParkingLot.create(slots);

        Vehicle bike = new Vehicle("BIKE-101", VehicleType.TWO_WHEELER);
        Vehicle car = new Vehicle("CAR-501", VehicleType.CAR);
        Vehicle bus = new Vehicle("BUS-777", VehicleType.BUS);

        LocalDateTime t0 = LocalDateTime.now();

        ParkingTicket bikeTicket = parkingLot.park(bike, t0, SlotType.SMALL, "GATE-A");
        ParkingTicket carTicket = parkingLot.park(car, t0.plusMinutes(5), SlotType.MEDIUM, "GATE-B");
        ParkingTicket busTicket = parkingLot.park(bus, t0.plusMinutes(10), SlotType.LARGE, "GATE-A");

        System.out.println("Allocated slots:");
        System.out.println(bikeTicket.getTicketId() + " -> " + bikeTicket.getParkingSlot().getSlotId() + " (" + bikeTicket.getAllocatedSlotType() + ")");
        System.out.println(carTicket.getTicketId() + " -> " + carTicket.getParkingSlot().getSlotId() + " (" + carTicket.getAllocatedSlotType() + ")");
        System.out.println(busTicket.getTicketId() + " -> " + busTicket.getParkingSlot().getSlotId() + " (" + busTicket.getAllocatedSlotType() + ")");

        System.out.println("Status: " + parkingLot.status());

        double bikeBill = parkingLot.exit(bikeTicket, t0.plusHours(2));
        double carBill = parkingLot.exit(carTicket, t0.plusHours(3));
        double busBill = parkingLot.exit(busTicket, t0.plusHours(4));

        System.out.println("Bills:");
        System.out.println("Bike bill = " + bikeBill);
        System.out.println("Car bill = " + carBill);
        System.out.println("Bus bill = " + busBill);
        System.out.println("Status after exits: " + parkingLot.status());
    }

    private static Map<String, Integer> gateDistances(int gateA, int gateB) {
        Map<String, Integer> map = new HashMap<>();
        map.put("GATE-A", gateA);
        map.put("GATE-B", gateB);
        return map;
    }
}
