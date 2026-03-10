public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        InputConnectable projector = reg.getDevice(InputConnectable.class);
        PowerDevices projectorPower = reg.getDevice(Projector.class);

        projectorPower.powerOn();
        projector.connectInput("HDMI-1");

        BrightnessController lights = reg.getDevice(BrightnessController.class);
        lights.setBrightness(60);

        TemperatureController ac = reg.getDevice(TemperatureController.class);
        ac.setTemperatureC(24);

        AttendanceScanner scanner = reg.getDevice(AttendanceScanner.class);
        System.out.println("Attendance scanned: present=" + scanner.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getDevice(Projector.class).powerOff();
        reg.getDevice(LightsPanel.class).powerOff();
        reg.getDevice(AirConditioner.class).powerOff();
    }
}
