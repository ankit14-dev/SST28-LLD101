package ParkingSystem;

public enum SlotType {
    SMALL,
    MEDIUM,
    LARGE;

    public boolean canAccommodate(SlotType required) {
        return this.ordinal() >= required.ordinal();
    }
}
