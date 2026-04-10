package ParkingSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ParkingSlot {
    private final String slotId;
    private final SlotType slotType;
    private final Map<String, Integer> distanceByGate;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSlot(String slotId, SlotType slotType, Map<String, Integer> distanceByGate) {
        this.slotId = Objects.requireNonNull(slotId, "slotId");
        this.slotType = Objects.requireNonNull(slotType, "slotType");
        this.distanceByGate = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(distanceByGate, "distanceByGate")));
        this.isOccupied = false;
    }

    public abstract double getHourlyRate();

    public String getSlotId() {
        return slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySlot(Vehicle vehicle) {
        this.parkedVehicle = Objects.requireNonNull(vehicle, "vehicle");
        isOccupied = true;
    }

    public void vacateSlot() {
        this.parkedVehicle = null;
        isOccupied = false;
    }

    public int distanceFromGate(String gateId) {
        Integer distance = distanceByGate.get(gateId);
        if (distance == null) {
            throw new IllegalArgumentException("Unknown gateId: " + gateId);
        }
        return distance;
    }

    public boolean canFit(Vehicle vehicle, SlotType requestedSlotType) {
        Objects.requireNonNull(vehicle, "vehicle");
        Objects.requireNonNull(requestedSlotType, "requestedSlotType");

        SlotType minByVehicle = vehicle.getVehicleType().minimumRequiredSlotType();
        SlotType effectiveMinimum = requestedSlotType.ordinal() > minByVehicle.ordinal() ? requestedSlotType : minByVehicle;
        return this.slotType.canAccommodate(effectiveMinimum);
    }

}
