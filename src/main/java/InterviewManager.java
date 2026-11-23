public class InterviewManager {
    private InterviewDAO dao = new InterviewDAO();

    public void schedule(int applicationId, String date) throws Exception {
        dao.save(new Interview(applicationId, date, "Scheduled"));
    }

    public void list() throws Exception {
        dao.printAll();
    }
}
