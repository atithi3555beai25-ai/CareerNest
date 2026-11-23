public class Recruiter extends Person {
    private int companyId;

    public Recruiter(int id, String name, int companyId) {
        super(id, name);
        this.companyId = companyId;
    }

    public int getCompanyId() {
        return companyId;
    }
}
