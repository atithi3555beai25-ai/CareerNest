public class CompanyManager {
    private CompanyRepository dao = new CompanyRepository();

    public void add(Company c) throws Exception {
        dao.save(c);
    }

    public void list() throws Exception {
        for (Company c : dao.all())
            System.out.println(c);
    }
}
