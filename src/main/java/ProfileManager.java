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
        if (studentId <= 0) {
            return null;
        }
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

    public void addSkill(Student student, String skillName) {
        getOrCreateProfile(student).addSkill(skillName);
    }

    public void addCertification(Student student, Certification certification) {
        getOrCreateProfile(student).addCertification(certification);
    }

    public void addCertification(Student student, String certificationTitle) {
        getOrCreateProfile(student).addCertification(certificationTitle);
    }

    public boolean removeSkill(int studentId, String skillName) {
        StudentProfile profile = findProfile(studentId);
        return profile != null && profile.removeSkill(skillName);
    }

    public boolean removeCertification(int studentId, String certificationTitle) {
        StudentProfile profile = findProfile(studentId);
        return profile != null && profile.removeCertification(certificationTitle);
    }

    public ArrayList<Skill> getSkills(int studentId) {
        StudentProfile profile = findProfile(studentId);
        return profile != null ? profile.getSkills() : new ArrayList<>();
    }

    public ArrayList<Certification> getCertifications(int studentId) {
        StudentProfile profile = findProfile(studentId);
        return profile != null ? profile.getCertifications() : new ArrayList<>();
    }

    public void showSkills(int studentId) {
        StudentProfile profile = findProfile(studentId);
        if (profile == null) {
            System.out.println("Student profile not found.");
            return;
        }
        profile.displaySkills();
    }

    public void showCertifications(int studentId) {
        StudentProfile profile = findProfile(studentId);
        if (profile == null) {
            System.out.println("Student profile not found.");
            return;
        }
        profile.displayCertifications();
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
