import java.util.ArrayList;

public class CompanyManager {
    private CompanyRepository dao = new CompanyRepository();

    public void add(Company c) throws Exception {
        dao.save(c);
    }

    public void list() throws Exception {
        for (Company c : dao.all())
            System.out.println(c);
    }

    public void listSorted() throws Exception {

    ArrayList<Company> companies =
        dao.sortedCompanies();

    if (companies.isEmpty()) {

        System.out.println("No companies found.");
        return;
    }

    System.out.println("\nCompanies (Alphabetical Order)");

    for (Company company : companies) {

        System.out.println(
            company.getId() + " - " + company.getName()
        );
    }
}
}
