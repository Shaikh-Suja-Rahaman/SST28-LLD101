// Immutable data class for booking receipt
// Reasoning: Immutability ensures safety, clarity, and thread-safety for value objects.
public class BookingReceipt {
    public final String id;
    public final double fare;
    public BookingReceipt(String id, double fare) { this.id = id; this.fare = fare; }
}
