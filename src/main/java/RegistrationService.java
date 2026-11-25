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
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (!userValidator.validName(s.getName())) {
            throw new IllegalArgumentException("Name required");
        }
        validator.validateStudent(s);

        if (manager.exists(s.getId())) {
            throw new IllegalArgumentException("Student ID " + s.getId() + " is already registered.");
        }

        manager.add(s);
    }
}
