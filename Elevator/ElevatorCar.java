package Elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class ElevatorCar {
    private final int id;
    private int currentFloor;
    private ElevatorState state;
    private double currentWeight;
    private final double maxWeight;

    private final PriorityQueue<Integer> upQueue;
    private final PriorityQueue<Integer> downQueue;

    private final Door door;
    private final Motor motor;

    public ElevatorCar(int id, int currentFloor, double maxWeight) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.state = ElevatorState.IDLE;
        this.currentWeight = 0;
        this.maxWeight = maxWeight;
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>(Collections.reverseOrder());
        this.door = new Door();
        this.motor = new Motor();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        if (currentWeight < 0 || currentWeight > maxWeight) {
            throw new IllegalArgumentException("Invalid currentWeight");
        }
        this.currentWeight = currentWeight;
    }

    public void addExternalRequest(int floor, Direction dir) {
        Objects.requireNonNull(dir, "dir");
        enqueueFloor(floor);
    }

    public void addInternalRequest(int floor) {
        enqueueFloor(floor);
    }

    private void enqueueFloor(int floor) {
        if (floor == currentFloor) {
            door.open();
            door.close();
            return;
        }

        if (floor > currentFloor) {
            upQueue.offer(floor);
        } else {
            downQueue.offer(floor);
        }
        updateStateForPendingRequests();
    }

    public void move() {
        if (state == ElevatorState.MAINTENANCE) {
            return;
        }

        Integer target = pickNextTarget();
        if (target == null) {
            state = ElevatorState.IDLE;
            motor.stop();
            return;
        }

        if (target > currentFloor) {
            state = ElevatorState.MOVING_UP;
            motor.moveUp();
            currentFloor++;
        } else if (target < currentFloor) {
            state = ElevatorState.MOVING_DOWN;
            motor.moveDown();
            currentFloor--;
        }

        if (currentFloor == target) {
            if (state == ElevatorState.MOVING_UP) {
                upQueue.poll();
            } else if (state == ElevatorState.MOVING_DOWN) {
                downQueue.poll();
            }
            motor.stop();
            door.open();
            door.close();
        }

        updateStateForPendingRequests();
    }

    public boolean hasPendingRequests() {
        return !upQueue.isEmpty() || !downQueue.isEmpty();
    }

    public State snapshot() {
        return new State(id, currentFloor, state, new ArrayList<>(upQueue), new ArrayList<>(downQueue));
    }

    private Integer pickNextTarget() {
        if (state == ElevatorState.MOVING_UP) {
            if (!upQueue.isEmpty()) {
                return upQueue.peek();
            }
            if (!downQueue.isEmpty()) {
                return downQueue.peek();
            }
        }

        if (state == ElevatorState.MOVING_DOWN) {
            if (!downQueue.isEmpty()) {
                return downQueue.peek();
            }
            if (!upQueue.isEmpty()) {
                return upQueue.peek();
            }
        }

        if (!upQueue.isEmpty()) {
            return upQueue.peek();
        }
        if (!downQueue.isEmpty()) {
            return downQueue.peek();
        }
        return null;
    }

    private void updateStateForPendingRequests() {
        if (!upQueue.isEmpty() && (state == ElevatorState.IDLE || state == ElevatorState.MOVING_UP)) {
            state = ElevatorState.MOVING_UP;
            return;
        }
        if (!downQueue.isEmpty() && (state == ElevatorState.IDLE || state == ElevatorState.MOVING_DOWN)) {
            state = ElevatorState.MOVING_DOWN;
            return;
        }
        if (upQueue.isEmpty() && downQueue.isEmpty()) {
            state = ElevatorState.IDLE;
        }
    }
}