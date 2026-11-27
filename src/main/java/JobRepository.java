import java.util.ArrayList;
import java.util.HashSet;
public class JobRepository {
    public ArrayList<Job> all() throws Exception {
        ArrayList<Job> list = new ArrayList<>();
        for (String row : FileStorage.read("jobs")) {
            String[] a = row.split("\\|", -1);
            list.add(new Job(Integer.parseInt(a[0]), Integer.parseInt(a[1]), a[2], a[3], Double.parseDouble(a[4]), Integer.parseInt(a[5])));
        }
        return list;
    }
    private void store(ArrayList<Job> list) throws Exception {
        ArrayList<String> rows = new ArrayList<>();
        for (Job item : list) rows.add("" + item.getId() + "|" + item.getCompanyId() + "|" + item.getTitle() + "|" + item.getBranch() + "|" + item.getMinCgpa() + "|" + item.getMaxBacklogs());
        FileStorage.write("jobs", rows);
    }
    public Job find(int id) throws Exception {
        for (Job item : all()) if (item.getId() == id) return item;
        return null;
    }
    public void save(Job item) throws Exception {
        RecordValidator.id(item.getId());
        if (find(item.getId()) != null) throw new IllegalArgumentException("ID already exists");
        RecordValidator.text(item.getTitle());
        RecordValidator.text(item.getBranch());
        if (new CompanyRepository().find(item.getCompanyId()) == null) throw new IllegalArgumentException("Company not found");
        if (!new InputValidator().cgpa(item.getMinCgpa()) || item.getMaxBacklogs() < 0) throw new IllegalArgumentException("Invalid eligibility limits");
        ArrayList<Job> list = all();
        list.add(item);
        store(list);
    }
    public HashSet<String> getUniqueBranches() throws Exception {
    HashSet<String> branches = new HashSet<>();

    for (Job item : all()) {
        String branch = item.getBranch();

        if (branch != null && !branch.trim().isEmpty()) {
            branches.add(branch.trim().toUpperCase());
        }
    }

    return branches;
}
}
