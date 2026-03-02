import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public <T> T getFirstByCapability(Class<T> capability) {
        for (Object d : devices) {
            if (capability.isInstance(d)) return capability.cast(d);
        }
        throw new IllegalStateException("No device with capability: " + capability.getSimpleName());
    }

    public <T> List<T> getAllByCapability(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (Object d : devices) {
            if (capability.isInstance(d)) result.add(capability.cast(d));
        }
        return result;
    }
}
