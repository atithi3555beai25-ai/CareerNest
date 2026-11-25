/**
 * Validates user inputs and student domain data for CareerNest.
 */
public class InputValidator {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_BRANCH_LENGTH = 50;
    private static final int MIN_YEAR = 1950;
    private static final int MAX_YEAR = 2100;

    public boolean positive(int n) {
        return n > 0;
    }

    public boolean isFiniteNumber(double value) {
        return Double.isFinite(value);
    }

    public boolean cgpa(double value) {
        return isFiniteNumber(value) && value >= 0.0 && value <= 10.0;
    }

    public boolean nonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public boolean validId(int id) {
        return positive(id);
    }

    public boolean validName(String name) {
        if (!nonEmpty(name)) {
            return false;
        }
        String trimmed = name.trim();
        return trimmed.length() <= MAX_NAME_LENGTH
                && trimmed.matches("[A-Za-z][A-Za-z .'-]*");
    }

    public boolean validBranch(String branch) {
        if (!nonEmpty(branch)) {
            return false;
        }
        String trimmed = branch.trim();
        return trimmed.length() <= MAX_BRANCH_LENGTH
                && trimmed.matches("[A-Za-z0-9 &/.-]+")
                && trimmed.matches(".*[A-Za-z].*");
    }

    public boolean validBacklogs(int backlogs) {
        return backlogs >= 0;
    }

    public boolean validYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    /**
     * Validates all required fields of a Student object.
     *
     * @param student student to validate
     * @throws IllegalArgumentException if the student or any field is invalid
     */
    public void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (!validId(student.getId())) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        if (!validName(student.getName())) {
            throw new IllegalArgumentException(
                    "Student name must be 1-" + MAX_NAME_LENGTH + " valid alphabetic characters.");
        }
        if (!validBranch(student.getBranch())) {
            throw new IllegalArgumentException(
                    "Student branch must be 1-" + MAX_BRANCH_LENGTH + " valid characters.");
        }
        if (!cgpa(student.getCgpa())) {
            throw new IllegalArgumentException("CGPA must be a finite number between 0.0 and 10.0.");
        }
        if (!validBacklogs(student.getBacklogs())) {
            throw new IllegalArgumentException("Backlogs cannot be negative.");
        }
    }
}
