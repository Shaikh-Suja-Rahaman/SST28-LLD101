// ReportWrite: abstraction for report writing
// Reason: Allows pipeline to depend on interface, not concrete
public interface ReportWrite {
    String write(Submission s, int plag, int code);
}
