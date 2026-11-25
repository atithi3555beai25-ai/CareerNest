import java.util.ArrayList;

/**
 * Manages student records by coordinating validation and StudentDAO persistence.
 */
public class StudentManager {
    private final StudentDAO dao = new StudentDAO();
    private final InputValidator validator = new InputValidator();

    public void add(Student s) throws Exception {
        validator.validateStudent(s);
        dao.save(s);
    }

    public Student find(int id) throws Exception {
        if (!validator.validId(id)) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        return dao.find(id);
    }

    public boolean exists(int id) throws Exception {
        if (!validator.validId(id)) {
            return false;
        }
        return dao.exists(id);
    }

    public ArrayList<Student> getAll() throws Exception {
        return dao.all();
    }

    public ArrayList<Student> findByBranch(String branch) throws Exception {
        if (!validator.validBranch(branch)) {
            throw new IllegalArgumentException("Branch cannot be empty or invalid.");
        }
        return dao.findByBranch(branch.trim());
    }

    public boolean update(Student s) throws Exception {
        validator.validateStudent(s);
        return dao.update(s);
    }

    public boolean delete(int id) throws Exception {
        if (!validator.validId(id)) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        return dao.delete(id);
    }

    public void list() throws Exception {
        System.out.println("Checking students...");

        ArrayList<Student> students = dao.all();

        System.out.println("Total students: " + students.size());

        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

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
