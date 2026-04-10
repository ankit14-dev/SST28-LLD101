package Elevator;

public class FloorPanel {
    private final int floor;

    public FloorPanel(int floor) {
        this.floor = floor;
    }

    public void pressUp() {
        ElevatorSystem.getInstance().requestElevator(floor, Direction.UP);
    }

    public void pressDown() {
        ElevatorSystem.getInstance().requestElevator(floor, Direction.DOWN);
    }
}