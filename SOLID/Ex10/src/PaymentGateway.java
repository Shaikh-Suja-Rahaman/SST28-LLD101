// Implements IPaymentGateway for DIP
// Reasoning: Now used via abstraction in booking logic, enabling testability and extension.
public class PaymentGateway implements IPaymentGateway {
    public String charge(String studentId, double amount) {
        // fake deterministic txn
        return "TXN-9001";
    }
}
