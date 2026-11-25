import java.util.ArrayList;

/**
 * Manages StudentProfile instances and their education, skills, and certifications.
 */
public class ProfileManager {
    private final ArrayList<StudentProfile> profiles = new ArrayList<>();

    public void showStudent(Student student) {
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        StudentProfile existing = findProfile(student.getId());
        if (existing != null) {
            existing.setStudent(student);
            existing.show();
        } else {
            new StudentProfile(student).show();
        }
    }

    public StudentProfile getOrCreateProfile(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        StudentProfile existing = findProfile(student.getId());
        if (existing != null) {
            existing.setStudent(student);
            return existing;
        }
        StudentProfile created = new StudentProfile(student);
        profiles.add(created);
        return created;
    }

    public StudentProfile findProfile(int studentId) {
        for (StudentProfile profile : profiles) {
            if (profile.getStudent().getId() == studentId) {
                return profile;
            }
        }
        return null;
    }

    public void addEducation(Student student, Education education) {
        getOrCreateProfile(student).addEducation(education);
    }

    public void addSkill(Student student, Skill skill) {
        getOrCreateProfile(student).addSkill(skill);
    }

    public void addCertification(Student student, Certification certification) {
        getOrCreateProfile(student).addCertification(certification);
    }

    public ArrayList<StudentProfile> getAllProfiles() {
        return new ArrayList<>(profiles);
    }

    public void listProfiles() {
        if (profiles.isEmpty()) {
            System.out.println("No student profiles available.");
            return;
        }
        for (StudentProfile profile : profiles) {
            profile.show();
        }
    }
}
