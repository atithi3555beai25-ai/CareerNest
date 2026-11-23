public class UserValidator {
    public boolean validName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
