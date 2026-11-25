/**
 * Base class representing a person in the CareerNest system.
 * Demonstrates encapsulation and inheritance for Student, User, and Recruiter.
 */
public class Person {
    protected int id;
    protected String name;

    /**
     * Constructs a Person with a validated ID and name.
     *
     * @param id   positive unique identifier
     * @param name non-empty full name
     */
    public Person(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return id + " | " + name;
    }
}
