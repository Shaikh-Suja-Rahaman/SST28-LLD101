public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        EligibilityRepository repo = new FakeEligibilityStore();
        Evaluation eval = new Evaluation();
        ReportPrinter printer = new ReportPrinter();
        EligibilityEngine engine = new EligibilityEngine(repo, eval, printer);


        engine.runAndPrint(s);
    }
}
