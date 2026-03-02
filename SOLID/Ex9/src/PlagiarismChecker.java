// PlagiarismChecker: now implements PlagiarismCheck interface
// Reason: Pipeline can depend on abstraction, not concrete
public class PlagiarismChecker implements PlagiarismCheck {
    @Override
    public int check(Submission s) {
        // fake score: lower is "better", but pipeline adds it anyway (smell)
        return (s.code.contains("class") ? 12 : 40);
    }
}
