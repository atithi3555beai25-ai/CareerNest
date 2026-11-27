import java.util.HashSet;
public class JobManager {
    private JobRepository dao = new JobRepository();

    public void add(Job j) throws Exception {
        dao.save(j);
    }

    public void list() throws Exception {
        for (Job j : dao.all())
            System.out.println(j);
    }
    public void showUniqueBranches() throws Exception {
    HashSet<String> branches = dao.getUniqueBranches();

    System.out.println("\n===== UNIQUE JOB BRANCHES =====");

    if (branches.isEmpty()) {
        System.out.println("No branches available.");
        return;
    }

    for (String branch : branches) {
        System.out.println(branch);
    }
}
}
