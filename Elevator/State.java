package Elevator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class State {
	private final int elevatorId;
	private final int currentFloor;
	private final ElevatorState elevatorState;
	private final List<Integer> upStops;
	private final List<Integer> downStops;

	public State(int elevatorId, int currentFloor, ElevatorState elevatorState, List<Integer> upStops, List<Integer> downStops) {
		this.elevatorId = elevatorId;
		this.currentFloor = currentFloor;
		this.elevatorState = Objects.requireNonNull(elevatorState, "elevatorState");
		this.upStops = Collections.unmodifiableList(Objects.requireNonNull(upStops, "upStops"));
		this.downStops = Collections.unmodifiableList(Objects.requireNonNull(downStops, "downStops"));
	}

	public int getElevatorId() {
		return elevatorId;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public ElevatorState getElevatorState() {
		return elevatorState;
	}

	public List<Integer> getUpStops() {
		return upStops;
	}

	public List<Integer> getDownStops() {
		return downStops;
	}

	@Override
	public String toString() {
		return "State{" +
			"elevatorId=" + elevatorId +
			", currentFloor=" + currentFloor +
			", elevatorState=" + elevatorState +
			", upStops=" + upStops +
			", downStops=" + downStops +
			'}';
	}
}
