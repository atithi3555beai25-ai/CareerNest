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

        StringBuilder reasons = new StringBuilder();

        for (EligibilityRule rule : rules) {

            if (!rule.check(student, job)) {

                if (reasons.length() > 0) {
                    reasons.append(", ");
                }

                reasons.append(rule.failureMessage());
            }
        }

        if (reasons.length() > 0) {
            return new EligibilityResult(false,
                    "Not eligible: " + reasons.toString());
        }

        return new EligibilityResult(true, "Eligible for this job");
    }
}