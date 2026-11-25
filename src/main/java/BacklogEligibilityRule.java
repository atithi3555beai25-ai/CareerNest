public class BacklogEligibilityRule implements EligibilityRule {
    @Override
    public boolean check(Student student, Job job) {
        return student.getBacklogs() <= job.getMaxBacklogs();
    }

    @Override
    public String failureMessage() {
        return "Too many backlogs";
    }
}
