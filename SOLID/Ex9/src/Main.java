public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        // Inject dependencies: DIP — easy to swap implementations for testing
        PlagiarismCheck checker = new PlagiarismChecker();
        CodeGrade grader = new CodeGrader();
        ReportWrite writer = new ReportWriter();
        Rubric rubric = new Rubric();
        new EvaluationPipeline(checker, grader, writer, rubric).evaluate(sub);
    }
}
