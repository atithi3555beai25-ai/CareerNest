public class CompanyManager {
    private CompanyDAO dao = new CompanyDAO();

    public void add(Company c) throws Exception {
        dao.save(c);
    }

    public void list() throws Exception {
        for (Company c : dao.all())
            System.out.println(c);
    }
}
