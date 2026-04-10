package Elevator;

import java.util.List;
import java.util.Objects;

public class NearestCarStrategy implements SchedulingStrategy {
	@Override
	public ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction) {
		Objects.requireNonNull(elevators, "elevators");
		Objects.requireNonNull(direction, "direction");
		if (elevators.isEmpty()) {
			throw new IllegalStateException("No elevators configured");
		}

		ElevatorCar bestCar = null;
		int bestScore = Integer.MAX_VALUE;

		for (ElevatorCar car : elevators) {
			int score = score(car, floor, direction);
			if (score < bestScore) {
				bestScore = score;
				bestCar = car;
			}
		}

		return bestCar;
	}

	private int score(ElevatorCar car, int floor, Direction direction) {
		int distance = Math.abs(car.getCurrentFloor() - floor);

		if (car.getState() == ElevatorState.IDLE) {
			return distance;
		}

		boolean movingTowardRequest =
			(car.getState() == ElevatorState.MOVING_UP && direction == Direction.UP && floor >= car.getCurrentFloor())
				|| (car.getState() == ElevatorState.MOVING_DOWN && direction == Direction.DOWN && floor <= car.getCurrentFloor());

		return movingTowardRequest ? distance + 2 : distance + 1000;
	}
}
