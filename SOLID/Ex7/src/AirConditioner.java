public class AirConditioner implements PowerDevices, TemperatureController {
    @Override
    public void powerOn() {
        /* ok */
        System.out.println("AC ON");
    }

    @Override
    public void powerOff() {
        System.out.println("AC OFF");
    }

    @Override
    public void setTemperatureC(int c) {
        System.out.println("AC set to " + c + "C");
    }
}
