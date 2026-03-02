// import java.util.*;

public class EligibilityEngine {
    private final EligibilityRepository store;
    private final Evaluation eval;
    private final ReportPrinter printer;

    public EligibilityEngine(EligibilityRepository store, Evaluation eval, ReportPrinter printer) {
        this.store = store;
        this.eval = eval;
        this.printer = printer;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = eval.evaluate(s); // giant conditional inside
        printer.print(s, r);
        store.save(s.rollNo, r.status);
    }


}
