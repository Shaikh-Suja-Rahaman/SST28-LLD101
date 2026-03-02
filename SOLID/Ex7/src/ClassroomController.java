public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Switchable pj = reg.getFirstByCapability(Switchable.class);
        pj.powerOn();
        reg.getFirstByCapability(InputSource.class).connectInput("HDMI-1");

        reg.getFirstByCapability(Dimmable.class).setBrightness(60);

        reg.getFirstByCapability(TemperatureControllable.class).setTemperatureC(24);

        System.out.println("Attendance scanned: present=" + reg.getFirstByCapability(AttendanceSensor.class).scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (Switchable s : reg.getAllByCapability(Switchable.class)) {
            s.powerOff();
        }
    }
}
