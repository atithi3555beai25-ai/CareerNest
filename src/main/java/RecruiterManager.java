public class RecruiterManager {
    public Recruiter create(int id, String name, int companyId) {
        return new Recruiter(id, name, companyId);
    }
}
