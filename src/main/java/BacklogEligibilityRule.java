public class BacklogEligibilityRule {
    public boolean check(Student s,Job j){
        return s.getBacklogs()<=j.getMaxBacklogs();
    }
 }
