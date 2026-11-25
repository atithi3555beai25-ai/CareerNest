public interface EligibilityRule {
    boolean check(Student student, Job job);
    String failureMessage();
}
