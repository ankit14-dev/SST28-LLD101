package ParkingSystem;

import java.util.Map;

public class MediumSlot extends ParkingSlot {
    private static final double HOURLY_RATE = 20;

    public MediumSlot(String slotId, Map<String, Integer> distanceByGate) {
        super(slotId, SlotType.MEDIUM, distanceByGate);
    }

    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
}
