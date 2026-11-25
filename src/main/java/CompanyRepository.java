import java.util.ArrayList;
public class CompanyRepository {
    public ArrayList<Company> all() throws Exception {
        ArrayList<Company> list = new ArrayList<>();
        for (String row : FileStorage.read("companys")) {
            String[] a = row.split("\\|", -1);
            list.add(new Company(Integer.parseInt(a[0]), a[1]));
        }
        return list;
    }
    private void store(ArrayList<Company> list) throws Exception {
        ArrayList<String> rows = new ArrayList<>();
        for (Company item : list) rows.add("" + item.getId() + "|" + item.getName());
        FileStorage.write("companys", rows);
    }
    public Company find(int id) throws Exception {
        for (Company item : all()) if (item.getId() == id) return item;
        return null;
    }
    public void save(Company item) throws Exception {
        RecordValidator.id(item.getId());
        if (find(item.getId()) != null) throw new IllegalArgumentException("ID already exists");
        RecordValidator.text(item.getName());
        ArrayList<Company> list = all();
        list.add(item);
        store(list);
    }
}
