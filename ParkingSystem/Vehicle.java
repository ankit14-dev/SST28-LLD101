package ParkingSystem;

import java.util.Objects;

public class Vehicle {
    private final String vehicleId;
    private final VehicleType vehicleType;

    public Vehicle(String vehicleId, VehicleType vehicleType) {
        this.vehicleId = Objects.requireNonNull(vehicleId, "vehicleId");
        this.vehicleType = Objects.requireNonNull(vehicleType, "vehicleType");
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
