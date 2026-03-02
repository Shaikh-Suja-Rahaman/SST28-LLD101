// CodeGrader: now implements CodeGrade interface
// Reason: Pipeline can depend on abstraction, not concrete
public class CodeGrader implements CodeGrade {
    @Override
    public int grade(Submission s, Rubric r) {
        // fake scoring (but deterministic)
        int base = Math.min(80, 50 + s.code.length() % 40);
        return base + r.bonus;
    }
}
