public class DisciplinaryRule implements EligibilityChecker{
  public String check(StudentProfile s){
    if (s.disciplinaryFlag != LegacyFlags.NONE) {
      return "disciplinary flag present";
    }
    return null; //pass

  }
}