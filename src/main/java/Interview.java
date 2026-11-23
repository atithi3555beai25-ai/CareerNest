public class Interview {
    private int applicationId;
    private String date, result;

    public Interview(int applicationId, String date, String result) {
        this.applicationId = applicationId;
        this.date = date;
        this.result = result;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }
}
