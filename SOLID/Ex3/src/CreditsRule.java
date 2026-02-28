public class CreditsRule implements EligibilityChecker{

  public String check(StudentProfile s){
        if (s.earnedCredits < 20) return "credits below 20";
        return null; // pass
  }
}
