/**
 * Represents a professional or academic certification earned by a student.
 */
public class Certification {
    private String title;
    private String issuer;
    private int year;

    /**
     * Constructs a Certification with only a title.
     *
     * @param title certification title
     */
    public Certification(String title) {
        this(title, "Not Specified", 0);
    }

    /**
     * Constructs a Certification with title, issuing organization, and year.
     *
     * @param title  certification title
     * @param issuer issuing organization
     * @param year   year earned (0 if unspecified)
     */
    public Certification(String title, String issuer, int year) {
        setTitle(title);
        setIssuer(issuer);
        setYear(year);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Certification title cannot be empty.");
        }
        this.title = title.trim();
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        if (issuer == null || issuer.trim().isEmpty()) {
            throw new IllegalArgumentException("Certification issuer cannot be empty.");
        }
        this.issuer = issuer.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year != 0 && (year < 1950 || year > 2100)) {
            throw new IllegalArgumentException("Certification year must be 0 or between 1950 and 2100.");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        if ("Not Specified".equals(issuer) && year == 0) {
            return title;
        }
        if (year == 0) {
            return title + " (" + issuer + ")";
        }
        return title + " - " + issuer + " (" + year + ")";
    }
}
