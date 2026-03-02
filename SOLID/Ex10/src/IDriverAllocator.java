// DIP abstraction: Interface for driver allocation
// Reasoning: Booking logic can use any allocator implementation, supporting testability and extension.
public interface IDriverAllocator {
    String allocate(String studentId);
}