import java.util.Objects;

/**
 * Represents a student registered in CareerNest.
 * Inherits id and name from Person, encapsulates academic attributes,
 * and defines natural ordering by CGPA descending (highest first).
 */
public class Student extends Person implements Comparable<Student> {
    private String branch;
    private double cgpa;
    private int backlogs;

    /**
     * Constructs a Student with validated academic details.
     *
     * @param id       positive student ID
     * @param name     student full name
     * @param branch   academic branch/department
     * @param cgpa     CGPA between 0.0 and 10.0
     * @param backlogs non-negative backlog count
     */
    public Student(int id, String name, String branch, double cgpa, int backlogs) {
        super(id, name);
        setBranch(branch);
        setCgpa(cgpa);
        setBacklogs(backlogs);
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        if (branch == null || branch.trim().isEmpty()) {
            throw new IllegalArgumentException("Branch cannot be empty.");
        }
        this.branch = branch.trim();
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        if (!Double.isFinite(cgpa) || cgpa < 0.0 || cgpa > 10.0) {
            throw new IllegalArgumentException("CGPA must be a finite number between 0.0 and 10.0.");
        }
        this.cgpa = cgpa;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(int backlogs) {
        if (backlogs < 0) {
            throw new IllegalArgumentException("Backlogs cannot be negative.");
        }
        this.backlogs = backlogs;
    }

    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + branch + " | " + cgpa + " | backlogs: " + backlogs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student other)) {
            return false;
        }
        return getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Compares students by CGPA in descending order (highest CGPA first),
     * breaking ties by student ID in ascending order.
     *
     * @param other another student to compare against
     * @return negative if this student has higher CGPA, positive if lower, 0 if equal
     */
    @Override
    public int compareTo(Student other) {
        if (other == null) {
            return -1;
        }
        int cgpaOrder = Double.compare(other.cgpa, this.cgpa);
        if (cgpaOrder != 0) {
            return cgpaOrder;
        }
        return Integer.compare(this.getId(), other.getId());
    }
}
