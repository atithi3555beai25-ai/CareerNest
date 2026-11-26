/**
 * Handles student registration workflow including validation and duplicate-ID checks.
 */
public class RegistrationService {
    private final StudentManager manager = new StudentManager();
    private final InputValidator validator = new InputValidator();
    private final UserValidator userValidator = new UserValidator();

    /**
     * Validates and registers a new student in the database.
     *
     * @param s student to register
     * @throws Exception if validation fails, the ID already exists, or persistence fails
     */
    public void register(Student s) throws Exception {
        validateForRegistration(s);

        if (manager.exists(s.getId())) {
            throw new IllegalArgumentException("Student ID " + s.getId() + " is already registered.");
        }

        manager.add(s);
    }

    /**
     * Validates raw registration fields, constructs a Student, and registers it.
     *
     * @param id       positive student ID
     * @param name     valid student name
     * @param branch   valid non-empty branch
     * @param cgpa     finite CGPA between 0.0 and 10.0
     * @param backlogs non-negative backlog count
     * @throws Exception if validation fails, the ID already exists, or persistence fails
     */
    public void register(int id, String name, String branch, double cgpa, int backlogs) throws Exception {
        validator.validateStudentFields(id, name, branch, cgpa, backlogs);
        register(new Student(id, name, branch, cgpa, backlogs));
    }

    /**
     * Validates a Student instance for registration.
     *
     * @param s student to validate
     * @throws IllegalArgumentException if the student or any required field is invalid
     */
    public void validateForRegistration(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (!userValidator.validName(s.getName())) {
            throw new IllegalArgumentException("Name required");
        }
        validator.validateStudent(s);
    }
}
