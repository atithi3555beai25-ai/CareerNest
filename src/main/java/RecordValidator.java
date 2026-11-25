public class RecordValidator {
    public static void text(String value) {
        if (value == null || value.trim().isEmpty() || value.contains("|") || value.contains("\n") || value.contains("\r"))
            throw new IllegalArgumentException("Enter nonempty text without | or line breaks");
    }
    public static void id(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
    }
}
