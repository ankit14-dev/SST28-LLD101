package Elevator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ElevatorCar car1 = new ElevatorCar(1, 0, 1000);
        ElevatorCar car2 = new ElevatorCar(2, 8, 1000);

        ElevatorSystem system = ElevatorSystem.getInstance();
        system.initialize(Arrays.asList(car1, car2), new NearestCarStrategy());

        FloorPanel floor3 = new FloorPanel(3);
        FloorPanel floor7 = new FloorPanel(7);

        floor3.pressUp();
        floor7.pressDown();

        CarPanel car1Panel = new CarPanel(car1);
        car1Panel.selectFloor(9);

        int maxSteps = 30;
        int step = 0;
        while (system.hasPendingWork() && step < maxSteps) {
            step++;
            system.step();
        }

        for (State snapshot : system.states()) {
            System.out.println(snapshot);
        }
    }
}
