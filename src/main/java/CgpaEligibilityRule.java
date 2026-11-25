public class CgpaEligibilityRule implements EligibilityRule {
    @Override
    public boolean check(Student student, Job job) {
        return student.getCgpa() >= job.getMinCgpa();
    }

    @Override
    public String failureMessage() {
        return "CGPA too low";
    }
}
