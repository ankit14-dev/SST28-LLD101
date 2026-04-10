package Elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ElevatorSystem {
    private static ElevatorSystem instance;
    private List<ElevatorCar> elevators;
    private SchedulingStrategy strategy;

    private ElevatorSystem() {
        this.elevators = new ArrayList<>();
        this.strategy = new NearestCarStrategy();
    }

    public static ElevatorSystem getInstance() {
        if (instance == null) instance = new ElevatorSystem();
        return instance;
    }

    public void initialize(List<ElevatorCar> elevators, SchedulingStrategy strategy) {
        this.elevators = new ArrayList<>(Objects.requireNonNull(elevators, "elevators"));
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public void addElevator(ElevatorCar elevatorCar) {
        this.elevators.add(Objects.requireNonNull(elevatorCar, "elevatorCar"));
    }

    public void requestElevator(int floor, Direction dir) {
        if (elevators.isEmpty()) {
            throw new IllegalStateException("No elevators configured");
        }
        ElevatorCar car = strategy.selectElevator(elevators, floor, dir);
        car.addExternalRequest(floor, dir);
    }

    public void step() {
        for (ElevatorCar car : elevators) {
            car.move();
        }
    }

    public boolean hasPendingWork() {
        for (ElevatorCar car : elevators) {
            if (car.hasPendingRequests()) {
                return true;
            }
        }
        return false;
    }

    public List<State> states() {
        List<State> snapshots = new ArrayList<>();
        for (ElevatorCar car : elevators) {
            snapshots.add(car.snapshot());
        }
        return Collections.unmodifiableList(snapshots);
    }
}