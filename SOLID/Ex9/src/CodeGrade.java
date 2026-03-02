// CodeGrade: abstraction for code grading
// Reason: Allows pipeline to depend on interface, not concrete
public interface CodeGrade {
    int grade(Submission s, Rubric r);
}
