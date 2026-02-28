import java.util.ArrayList;
import java.util.List;

public class Evaluation {




   EligibilityEngineResult evaluate(StudentProfile s){
       List<String> reasons = new ArrayList<>();
       String status = "ELIGIBLE";
        List<EligibilityChecker> rules = List.of(new DisciplinaryRule(), new CgrRule(), new AttendanceRule(), new CreditsRule());
        for (EligibilityChecker rule : rules) {
            // check each rule
            String result = rule.check(s);
            if (result != null) reasons.add(result);
        }

        if(reasons.size() > 0) status = "NOT_ELIGIBLE";

        return new EligibilityEngineResult(status, reasons);


  }
}
