public class BranchEligibilityRule implements EligibilityRule {
    @Override
    public boolean check(Student student, Job job) {
        return job.getBranch().trim().equalsIgnoreCase("ALL") ||
                student.getBranch().trim().equalsIgnoreCase(job.getBranch().trim());
    }

    @Override
    public String failureMessage() {
        return "Branch not allowed";
    }
}
