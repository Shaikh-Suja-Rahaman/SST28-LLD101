public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));

        // Wire up concrete implementations for abstractions
        IDistanceCalculator distanceCalculator = new DistanceCalculator();
        IDriverAllocator driverAllocator = new DriverAllocator();
        IPaymentGateway paymentGateway = new PaymentGateway();

        // Inject dependencies into booking service
        TransportBookingService svc = new TransportBookingService(distanceCalculator, driverAllocator, paymentGateway);
        svc.book(req);
        // Reasoning: Now, swapping implementations for testing or extension is trivial
    }
}
