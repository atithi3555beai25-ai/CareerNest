/**
 * Represents an academic qualification entry in a student's profile.
 */
public class Education {
    private String degree;
    private String institution;
    private int year;

    /**
     * Constructs an Education record with degree and graduation year.
     *
     * @param degree degree or program name
     * @param year   completion or expected graduation year
     */
    public Education(String degree, int year) {
        this(degree, "Not Specified", year);
    }

    /**
     * Constructs an Education record with degree, institution, and year.
     *
     * @param degree      degree or program name
     * @param institution school or university name
     * @param year        completion or expected graduation year
     */
    public Education(String degree, String institution, int year) {
        setDegree(degree);
        setInstitution(institution);
        setYear(year);
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        if (degree == null || degree.trim().isEmpty()) {
            throw new IllegalArgumentException("Degree cannot be empty.");
        }
        this.degree = degree.trim();
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        if (institution == null || institution.trim().isEmpty()) {
            throw new IllegalArgumentException("Institution cannot be empty.");
        }
        this.institution = institution.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1950 || year > 2100) {
            throw new IllegalArgumentException("Education year must be between 1950 and 2100.");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        if ("Not Specified".equals(institution)) {
            return degree + " (" + year + ")";
        }
        return degree + " - " + institution + " (" + year + ")";
    }
}
