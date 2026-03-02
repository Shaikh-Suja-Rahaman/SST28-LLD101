// ReportWriter: now implements ReportWrite interface
// Reason: Pipeline can depend on abstraction, not concrete
public class ReportWriter implements ReportWrite {
    @Override
    public String write(Submission s, int plag, int code) {
        // writes to a pretend file name
        return "report-" + s.roll + ".txt";
    }
}
