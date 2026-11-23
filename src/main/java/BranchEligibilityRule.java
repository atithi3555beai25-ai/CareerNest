public class BranchEligibilityRule {
    public boolean check(Student s,Job j){
        return j.getBranch().equalsIgnoreCase("ALL")||s.getBranch().equalsIgnoreCase(j.getBranch());} }
