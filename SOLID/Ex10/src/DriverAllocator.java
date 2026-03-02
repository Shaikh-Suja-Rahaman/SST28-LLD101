// Implements IDriverAllocator for DIP
// Reasoning: Now used via abstraction in booking logic, enabling testability and extension.
public class DriverAllocator implements IDriverAllocator {
    public String allocate(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
