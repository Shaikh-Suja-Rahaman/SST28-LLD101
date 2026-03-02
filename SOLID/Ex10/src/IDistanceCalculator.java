// DIP abstraction: Interface for distance calculation
// Reasoning: Allows booking logic to depend on abstraction, not concrete implementation. Enables test doubles and future extension.
public interface IDistanceCalculator {
    double km(GeoPoint a, GeoPoint b);
}