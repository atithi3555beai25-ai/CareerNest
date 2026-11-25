import java.util.ArrayList;

public class EligibilityRuleEngine {
    public EligibilityResult check(Student student, Job job) {
        if (student == null || job == null) {
            return new EligibilityResult(false, "Student or job not found");
        }

        ArrayList<EligibilityRule> rules = new ArrayList<>();
        rules.add(new CgpaEligibilityRule());
        rules.add(new BranchEligibilityRule());
        rules.add(new BacklogEligibilityRule());

        ArrayList<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            if (!rule.check(student, job)) {
                reasons.add(rule.failureMessage());
            }
        }

        if (!reasons.isEmpty()) {
            return new EligibilityResult(false,
                    "Not eligible: " + String.join(", ", reasons));
        }
        return new EligibilityResult(true, "Eligible for this job");
    }
}
