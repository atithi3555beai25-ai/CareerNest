public class RecruitmentDrive {
    private String title, date;

    public RecruitmentDrive(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String toString() {
        return title + " on " + date;
    }
}
