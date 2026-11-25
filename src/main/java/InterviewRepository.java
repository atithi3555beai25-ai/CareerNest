import java.util.ArrayList;
public class InterviewRepository {
    public ArrayList<Interview> all() throws Exception {
        ArrayList<Interview> list = new ArrayList<>();
        for (String row : FileStorage.read("interviews")) {
            String[] a = row.split("\\|", -1);
            list.add(new Interview(Integer.parseInt(a[0]), a[1], a[2]));
        }
        return list;
    }
    private void store(ArrayList<Interview> list) throws Exception {
        ArrayList<String> rows = new ArrayList<>();
        for (Interview item : list) rows.add("" + item.getApplicationId() + "|" + item.getDate() + "|" + item.getResult());
        FileStorage.write("interviews", rows);
    }
    public void save(Interview item) throws Exception {
        if (new ApplicationRepository().find(item.getApplicationId()) == null) throw new IllegalArgumentException("Application not found");
        RecordValidator.text(item.getDate());
        RecordValidator.text(item.getResult());
        ArrayList<Interview> list = all();
        list.add(item);
        store(list);
    }
    public void printAll() throws Exception {
        int id = 1;
        for (Interview item : all()) System.out.println(id++ + " | Application: " + item.getApplicationId() + " | " + item.getDate() + " | " + item.getResult());
    }
}
