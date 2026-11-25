/**
 * Validates user inputs and student domain data for CareerNest.
 */
public class InputValidator {

    public boolean positive(int n) {
        return n > 0;
    }

    public boolean cgpa(double value) {
        return !Double.isNaN(value) && value >= 0.0 && value <= 10.0;
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
        return name.trim().matches("[A-Za-z][A-Za-z .'-]*");
    }

    public boolean validBranch(String branch) {
        if (!nonEmpty(branch)) {
            return false;
        }
        return branch.trim().matches("[A-Za-z0-9 &/.-]+");
    }

    public boolean validBacklogs(int backlogs) {
        return backlogs >= 0;
    }

    public boolean validYear(int year) {
        return year >= 1950 && year <= 2100;
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
            throw new IllegalArgumentException("Student name must contain only valid alphabetic characters.");
        }
        if (!validBranch(student.getBranch())) {
            throw new IllegalArgumentException("Student branch cannot be empty or invalid.");
        }
        if (!cgpa(student.getCgpa())) {
            throw new IllegalArgumentException("CGPA must be between 0.0 and 10.0.");
        }
        if (!validBacklogs(student.getBacklogs())) {
            throw new IllegalArgumentException("Backlogs cannot be negative.");
        }
    }
}
