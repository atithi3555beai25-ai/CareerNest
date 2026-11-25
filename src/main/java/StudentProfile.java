import java.util.ArrayList;

/**
 * Composite profile for a Student containing education history, skills, and certifications.
 */
public class StudentProfile {
    private Student student;
    private ArrayList<Education> educationList;
    private ArrayList<Skill> skills;
    private ArrayList<Certification> certifications;

    /**
     * Constructs an empty profile for the given student.
     *
     * @param student student associated with this profile
     */
    public StudentProfile(Student student) {
        this(student, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Constructs a StudentProfile with pre-populated collections.
     *
     * @param student        student associated with this profile
     * @param educationList  list of education records
     * @param skills         list of skills
     * @param certifications list of certifications
     */
    public StudentProfile(Student student, ArrayList<Education> educationList, ArrayList<Skill> skills,
            ArrayList<Certification> certifications) {
        setStudent(student);
        this.educationList = educationList != null ? new ArrayList<>(educationList) : new ArrayList<>();
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
        this.certifications = certifications != null ? new ArrayList<>(certifications) : new ArrayList<>();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        this.student = student;
    }

    public ArrayList<Education> getEducationList() {
        return new ArrayList<>(educationList);
    }

    public ArrayList<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    public ArrayList<Certification> getCertifications() {
        return new ArrayList<>(certifications);
    }

    public void addEducation(Education education) {
        if (education == null) {
            throw new IllegalArgumentException("Education entry cannot be null.");
        }
        educationList.add(education);
    }

    public void addSkill(Skill skill) {
        if (skill == null) {
            throw new IllegalArgumentException("Skill cannot be null.");
        }
        skills.add(skill);
    }

    public void addCertification(Certification certification) {
        if (certification == null) {
            throw new IllegalArgumentException("Certification cannot be null.");
        }
        certifications.add(certification);
    }

    /**
     * Prints the student's details along with education, skills, and certifications.
     */
    public void show() {
        System.out.println("===== Student Profile =====");
        System.out.println(student);
        printSection("Education", educationList);
        printSection("Skills", skills);
        printSection("Certifications", certifications);
    }

    private <T> void printSection(String title, ArrayList<T> items) {
        System.out.println(title + ":");
        if (items.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        for (T item : items) {
            System.out.println("  - " + item);
        }
    }
}
