public class InputValidator {
    public boolean positive(int n) {
        return n > 0;
    }

    public boolean cgpa(double value) {
        return value >= 0 && value <= 10;
    }
}
