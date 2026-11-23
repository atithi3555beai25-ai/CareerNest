public class Job {
    private int id, companyId, maxBacklogs;
    private String title, branch;
    private double minCgpa;

    public Job(int id, int companyId, String title, String branch, double minCgpa, int maxBacklogs) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.branch = branch;
        this.minCgpa = minCgpa;
        this.maxBacklogs = maxBacklogs;
    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getTitle() {
        return title;
    }

    public String getBranch() {
        return branch;
    }

    public double getMinCgpa() {
        return minCgpa;
    }

    public int getMaxBacklogs() {
        return maxBacklogs;
    }

    public String toString() {
        return id + " | " + title + " | Branch: " + branch + " | Min CGPA: " + minCgpa + " | Max backlogs: "
                + maxBacklogs;
    }
}
