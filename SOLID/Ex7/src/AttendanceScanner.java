public class AttendanceScanner implements Switchable, AttendanceSensor {
    @Override public void powerOn() { /* ok */ }
    @Override public void powerOff() { /* no output */ }

    @Override public int scanAttendance() { return 3; }
}
