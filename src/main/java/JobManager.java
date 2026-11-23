public class JobManager {
    private JobDAO dao = new JobDAO();

    public void add(Job j) throws Exception {
        dao.save(j);
    }

    public void list() throws Exception {
        for (Job j : dao.all())
            System.out.println(j);
    }
}
