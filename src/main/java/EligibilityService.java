public class EligibilityService {
    public EligibilityResult check(Student s, Job j) {
        return new EligibilityRuleEngine().check(s, j);
    }
}
