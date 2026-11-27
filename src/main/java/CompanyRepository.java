import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;

public class CompanyRepository {

    // HashMap stores Company ID and Company object
    private HashMap<Integer, Company> companyMap = new HashMap<>();

    // HashSet stores unique Company IDs
    private HashSet<Integer> companyIds = new HashSet<>();


    // Read all companies
    public ArrayList<Company> all() throws Exception {

        ArrayList<Company> list = new ArrayList<>();

        companyMap.clear();
        companyIds.clear();

        for (String row : FileStorage.read("companys")) {

            String[] data = row.split("\\|", -1);

            if (data.length != 2) {
                continue;
            }

            // Convert String ID into Integer
            int id = Integer.parseInt(data[0]);

            String name = data[1];

            if (!companyIds.add(id)) {
                continue;
            }

            Company company = new Company(id, name);

            companyMap.put(id, company);

            list.add(company);
        }

        return list;
    }


    // Find company using HashMap
    public Company find(int id) throws Exception {

        all();

        return companyMap.get(id);
    }


    // Save company
    public void save(Company company) throws Exception {

        if (company == null) {
            throw new Exception("Company cannot be null");
        }

        int id = company.getId();

        String name = company.getName();

        if (id <= 0) {
            throw new Exception("Invalid Company ID");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Company name is required");
        }

        all();

        // Check duplicate using HashSet
        if (companyIds.contains(id)) {
            throw new Exception("Company ID already exists");
        }

        companyMap.put(id, company);

        companyIds.add(id);

        store(new ArrayList<>(companyMap.values()));
    }


    // Store companies in file
    private void store(ArrayList<Company> list) throws Exception {

        ArrayList<String> rows = new ArrayList<>();

        for (Company company : list) {

            rows.add(
                company.getId() + "|" + company.getName()
            );
        }

        FileStorage.write("companys", rows);
    }


    // Sort companies alphabetically using Comparator
    public ArrayList<Company> sortedCompanies() throws Exception {

        ArrayList<Company> list = all();

        list.sort(
            Comparator.comparing(
                Company::getName,
                String.CASE_INSENSITIVE_ORDER
            )
        );

        return list;
    }
}
