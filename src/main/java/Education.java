public class Education {
    private String degree;
    private int year;

    public Education(String degree, int year) {
        this.degree = degree;
        this.year = year;
    }

    public String toString() {
        return degree + " year " + year;
    }
}
