package Elevator;

import java.util.Objects;

public class CarPanel {
    private final ElevatorCar car;

    public CarPanel(ElevatorCar car) {
        this.car = Objects.requireNonNull(car, "car");
    }

    public void selectFloor(int floor) {
        car.addInternalRequest(floor);
    }

    public void pressAlarm() {
        System.out.println("Emergency triggered!");
    }
}