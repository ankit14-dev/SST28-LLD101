package ParkingSystem;

import java.util.Map;

public class SmallSlot extends ParkingSlot {
    private static final double HOURLY_RATE = 10;

    public double getHourlyRate() {
        return HOURLY_RATE;
    }

    public SmallSlot(String slotId, Map<String, Integer> distanceByGate) {
        super(slotId, SlotType.SMALL, distanceByGate);
    }
}
