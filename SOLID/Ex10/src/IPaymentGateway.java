// DIP abstraction: Interface for payment gateway
// Reasoning: Booking logic can use any payment implementation, supporting testability and extension.
public interface IPaymentGateway {
    String charge(String studentId, double amount);
}