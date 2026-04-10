package ParkingSystem;

public enum VehicleType {
    TWO_WHEELER,
    CAR,
    BUS;

    public SlotType minimumRequiredSlotType() {
        switch (this) {
            case TWO_WHEELER:
                return SlotType.SMALL;
            case CAR:
                return SlotType.MEDIUM;
            case BUS:
                return SlotType.LARGE;
            default:
                throw new IllegalStateException("Unsupported vehicle type: " + this);
        }
    }
}
