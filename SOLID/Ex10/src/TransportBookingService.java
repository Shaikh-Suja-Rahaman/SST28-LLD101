// DIP refactor: depends only on abstractions, injected via constructor
// Reasoning: Enables testability, extension, and separation of business logic from infrastructure.
public class TransportBookingService {
    private final IDistanceCalculator distanceCalculator;
    private final IDriverAllocator driverAllocator;
    private final IPaymentGateway paymentGateway;

    // Inject abstractions via constructor
    public TransportBookingService(IDistanceCalculator distanceCalculator,
                                   IDriverAllocator driverAllocator,
                                   IPaymentGateway paymentGateway) {
        this.distanceCalculator = distanceCalculator;
        this.driverAllocator = driverAllocator;
        this.paymentGateway = paymentGateway;
    }

    // Booking logic uses only abstractions
    public void book(TripRequest req) {
        double km = distanceCalculator.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverAllocator.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // pricing rule
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentGateway.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
