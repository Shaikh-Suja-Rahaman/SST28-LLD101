// Immutable data class for geo coordinates
// Reasoning: Immutability ensures safety, clarity, and thread-safety for value objects.
public class GeoPoint {
    public final double lat;
    public final double lon;
    public GeoPoint(double lat, double lon) { this.lat = lat; this.lon = lon; }
}
