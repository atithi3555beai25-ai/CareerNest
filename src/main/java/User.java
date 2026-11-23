public class User extends Person {
    private String role;

    public User(int id, String name, String role) {
        super(id, name);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
