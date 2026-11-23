public class EligibilityRuleEngine {
    public EligibilityResult check(Student s, Job j) {
        if (s == null || j == null)
            return new EligibilityResult(false, "Student or job not found");
        if (!new CgpaEligibilityRule().check(s, j))
            return new EligibilityResult(false, "CGPA too low");
        if (!new BranchEligibilityRule().check(s, j))
            return new EligibilityResult(false, "Branch not allowed");
        if (!new BacklogEligibilityRule().check(s, j))
            return new EligibilityResult(false, "Too many backlogs");
        return new EligibilityResult(true, "Eligible for this job");
    }
}
