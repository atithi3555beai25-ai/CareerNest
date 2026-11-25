/**
 * Represents a technical or soft skill in a student's profile.
 */
public class Skill {
    private String name;
    private String proficiency;

    /**
     * Constructs a Skill with default Intermediate proficiency.
     *
     * @param name skill name
     */
    public Skill(String name) {
        this(name, "Intermediate");
    }

    /**
     * Constructs a Skill with explicit name and proficiency level.
     *
     * @param name        skill name
     * @param proficiency skill proficiency level (e.g., Beginner, Intermediate, Advanced)
     */
    public Skill(String name, String proficiency) {
        setName(name);
        setProficiency(proficiency);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        if (proficiency == null || proficiency.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill proficiency cannot be empty.");
        }
        this.proficiency = proficiency.trim();
    }

    @Override
    public String toString() {
        return name + " [" + proficiency + "]";
    }
}
