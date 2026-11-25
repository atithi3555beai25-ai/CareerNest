public class InterviewManager {
    private InterviewRepository dao = new InterviewRepository();

    public void schedule(int applicationId, String date) throws Exception {
        dao.save(new Interview(applicationId, date, "Scheduled"));
    }

    public void list() throws Exception {
        dao.printAll();
    }
}
