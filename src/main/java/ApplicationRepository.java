import java.util.ArrayList;
public class ApplicationRepository {
    public ArrayList<Application> all() throws Exception {
        ArrayList<Application> list = new ArrayList<>();
        for (String row : FileStorage.read("applications")) {
            String[] a = row.split("\\|", -1);
            list.add(new Application(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]), a[3]));
        }
        return list;
    }
    private void store(ArrayList<Application> list) throws Exception {
        ArrayList<String> rows = new ArrayList<>();
        for (Application item : list) rows.add("" + item.getId() + "|" + item.getStudentId() + "|" + item.getJobId() + "|" + item.getStatus());
        FileStorage.write("applications", rows);
    }
    public Application find(int id) throws Exception {
        for (Application item : all()) if (item.getId() == id) return item;
        return null;
    }
    public boolean exists(int studentId, int jobId) throws Exception {
        for (Application a : all()) if (a.getStudentId() == studentId && a.getJobId() == jobId) return true;
        return false;
    }
    public void save(int studentId, int jobId) throws Exception {
        if (exists(studentId, jobId)) throw new DuplicateApplicationException("Already applied for this job");
        ArrayList<Application> list = all();
        int id = 1;
        for (Application a : list) if (a.getId() >= id) id = a.getId() + 1;
        list.add(new Application(id, studentId, jobId, ApplicationStatus.APPLIED));
        store(list);
    }
    public ArrayList<Application> forStudent(int studentId) throws Exception {
        ArrayList<Application> result = new ArrayList<>();
        for (Application a : all()) if (a.getStudentId() == studentId) result.add(a);
        return result;
    }
    public void printAll() throws Exception {
        ArrayList<Application> list = all();
        if (list.isEmpty()) System.out.println("No applications found");
        for (Application a : list) System.out.println(a);
    }
    public void updateStatus(int id, String status) throws Exception {
        if (!ApplicationStatus.SHORTLISTED.equals(status) && !ApplicationStatus.SELECTED.equals(status) && !ApplicationStatus.REJECTED.equals(status)) throw new IllegalArgumentException("Invalid status");
        ArrayList<Application> list = all();
        for (int i = 0; i < list.size(); i++) {
            Application a = list.get(i);
            if (a.getId() == id) {
                list.set(i, new Application(id, a.getStudentId(), a.getJobId(), status));
                store(list);
                return;
            }
        }
        throw new IllegalArgumentException("Application not found");
    }
}
