import java.util.ArrayList;

public class StudentManager {

    private StudentDAO dao = new StudentDAO();

    public void add(Student s) throws Exception {
        dao.save(s);
    }

    public Student find(int id) throws Exception {
        return dao.find(id);
    }

    public void list() throws Exception {

        System.out.println("Checking students...");

        ArrayList<Student> students = dao.all();

        System.out.println("Total students: " + students.size());

        if (students.size() == 0) {
            System.out.println("No students found!");
        } else {
            for (Student s : students) {
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Branch: " + s.getBranch());
                System.out.println("CGPA: " + s.getCgpa());
                System.out.println("Backlogs: " + s.getBacklogs());
                System.out.println("--------------------");
            }
        }
    }
}