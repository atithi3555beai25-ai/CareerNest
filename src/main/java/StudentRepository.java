import java.util.ArrayList;
public class StudentRepository {
    public ArrayList<Student> all() throws Exception {
        ArrayList<Student> list = new ArrayList<>();
        for (String row : FileStorage.read("students")) {
            String[] a = row.split("\\|", -1);
            list.add(new Student(Integer.parseInt(a[0]), a[1], a[2], Double.parseDouble(a[3]), Integer.parseInt(a[4])));
        }
        return list;
    }
    private void store(ArrayList<Student> list) throws Exception {
        ArrayList<String> rows = new ArrayList<>();
        for (Student item : list) rows.add("" + item.getId() + "|" + item.getName() + "|" + item.getBranch() + "|" + item.getCgpa() + "|" + item.getBacklogs());
        FileStorage.write("students", rows);
    }
    public Student find(int id) throws Exception {
        for (Student item : all()) if (item.getId() == id) return item;
        return null;
    }
    public void save(Student item) throws Exception {
        RecordValidator.id(item.getId());
        if (find(item.getId()) != null) throw new IllegalArgumentException("ID already exists");
        RecordValidator.text(item.getName());
        RecordValidator.text(item.getBranch());
        if (!new InputValidator().cgpa(item.getCgpa()) || item.getBacklogs() < 0) throw new IllegalArgumentException("Invalid CGPA or backlogs");
        ArrayList<Student> list = all();
        list.add(item);
        store(list);
    }
}
