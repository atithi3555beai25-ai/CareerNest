public class EligibilityResult {
    private boolean eligible;
    private String message;

    public EligibilityResult(boolean eligible, String message) {
        this.eligible = eligible;
        this.message = message;
    }

    public boolean isEligible() {
        return eligible;
    }

    public String getMessage() {
        return message;
    }
}
