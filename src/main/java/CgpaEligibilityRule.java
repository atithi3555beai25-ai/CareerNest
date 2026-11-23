public class CgpaEligibilityRule {
    public boolean check(Student s, Job j) {
        return s.getCgpa() >= j.getMinCgpa();
    }
}
