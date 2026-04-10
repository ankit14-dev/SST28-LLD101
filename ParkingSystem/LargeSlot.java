package ParkingSystem;

import java.util.Map;

public class LargeSlot extends ParkingSlot {
    private static final double HOURLY_RATE = 30;

    public LargeSlot(String slotId, Map<String, Integer> distanceByGate) {
        super(slotId, SlotType.LARGE, distanceByGate);
    }

    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
}
