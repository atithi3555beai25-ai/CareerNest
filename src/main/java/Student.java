public class Student extends Person {
    private String branch;
    private double cgpa;
    private int backlogs;

    public Student(int id, String name, String branch, double cgpa, int backlogs) {
        super(id, name);
        this.branch = branch;
        this.cgpa = cgpa;
        this.backlogs = backlogs;
    }

    public String getBranch() {
        return branch;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public String toString() {
        return id + " | " + name + " | " + branch + " | " + cgpa + " | backlogs: " + backlogs;
    }
}
