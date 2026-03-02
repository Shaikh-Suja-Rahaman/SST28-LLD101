// PlagiarismCheck: abstraction for plagiarism checking
// Reason: Allows pipeline to depend on interface, not concrete
public interface PlagiarismCheck {
    int check(Submission s);
}
