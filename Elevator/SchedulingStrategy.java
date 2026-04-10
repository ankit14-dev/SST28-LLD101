package Elevator;

import java.util.List;

public interface SchedulingStrategy {
	ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction);
}
