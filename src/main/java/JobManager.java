public class JobManager {
    private JobRepository dao = new JobRepository();

    public void add(Job j) throws Exception {
        dao.save(j);
    }

    public void list() throws Exception {
        for (Job j : dao.all())
            System.out.println(j);
    }
}
