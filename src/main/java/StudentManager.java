import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Manages student records by coordinating validation, sorting, and StudentDAO persistence.
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

    /**
     * Returns all students from the database sorted by CGPA in descending order.
     *
     * @return new ArrayList sorted from highest CGPA to lowest CGPA
     * @throws Exception if database access fails
     */
    public ArrayList<Student> getStudentsSortedByCgpa() throws Exception {
        return sortByCgpaDesc(dao.all());
    }

    /**
     * Returns a new list sorted by CGPA descending (highest first) without modifying the input list.
     *
     * @param students input list of students
     * @return new sorted ArrayList of non-null students
     */
    public ArrayList<Student> sortByCgpaDesc(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Student> sorted = new ArrayList<>();
        for (Student student : students) {
            if (student != null) {
                sorted.add(student);
            }
        }

        Comparator<Student> byCgpaDesc = (s1, s2) -> Double.compare(s2.getCgpa(), s1.getCgpa());
        Collections.sort(sorted, byCgpaDesc.thenComparingInt(Student::getId));
        return sorted;
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
        printStudents(dao.all());
    }

    /**
     * Displays all students sorted by CGPA in descending order.
     *
     * @throws Exception if database access fails
     */
    public void listSortedByCgpa() throws Exception {
        System.out.println("Checking students (sorted by CGPA descending)...");
        printStudents(getStudentsSortedByCgpa());
    }

    private void printStudents(ArrayList<Student> students) {
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
