package ParkingSystem;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ParkingManager {
    private final List<ParkingSlot> slots;

    public ParkingManager(List<ParkingSlot> slots) {
        this.slots = new ArrayList<>(Objects.requireNonNull(slots, "slots"));
    }

    public ParkingSlot getNearestCompatibleSlot(Vehicle vehicle, SlotType requestedSlotType, String entryGateId) {
        Objects.requireNonNull(vehicle, "vehicle");
        Objects.requireNonNull(requestedSlotType, "requestedSlotType");
        Objects.requireNonNull(entryGateId, "entryGateId");

        ParkingSlot best = null;
        int bestDistance = Integer.MAX_VALUE;

        for (ParkingSlot slot : slots) {
            if (slot.isOccupied()) {
                continue;
            }
            if (!slot.canFit(vehicle, requestedSlotType)) {
                continue;
            }

            int distance = slot.distanceFromGate(entryGateId);
            if (distance < bestDistance) {
                bestDistance = distance;
                best = slot;
            }
        }

        return best;
    }

    public ParkingSlot park(Vehicle vehicle, SlotType requestedSlotType, String entryGateId) {
        ParkingSlot slot = getNearestCompatibleSlot(vehicle, requestedSlotType, entryGateId);
        if (slot != null) {
            slot.occupySlot(vehicle);
            return slot;
        }
        return null;
    }

    public void vacateSlot(ParkingSlot slot) {
        Objects.requireNonNull(slot, "slot");
        slot.vacateSlot();
    }

    public Map<SlotType, Integer> availableSlotsByType() {
        Map<SlotType, Integer> counts = new EnumMap<>(SlotType.class);
        counts.put(SlotType.SMALL, 0);
        counts.put(SlotType.MEDIUM, 0);
        counts.put(SlotType.LARGE, 0);

        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                counts.put(slot.getSlotType(), counts.get(slot.getSlotType()) + 1);
            }
        }
        return counts;
    }
}
