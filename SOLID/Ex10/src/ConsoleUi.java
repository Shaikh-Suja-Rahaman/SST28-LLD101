// Smell: unused UI wrapper
// Reasoning: Not used in DIP refactor. Booking logic prints directly for demo simplicity.
// In a real system, UI would be injected as an abstraction for testability and separation.
public class ConsoleUi {
    public void print(String s) { System.out.println(s); }
}
