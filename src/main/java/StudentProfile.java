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
        setEducationList(educationList);
        setSkills(skills);
        setCertifications(certifications);
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

    public void setEducationList(ArrayList<Education> educationList) {
        this.educationList = new ArrayList<>();
        if (educationList == null) {
            return;
        }
        for (Education education : educationList) {
            if (education != null) {
                addEducation(education);
            }
        }
    }

    public ArrayList<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = new ArrayList<>();
        if (skills == null) {
            return;
        }
        for (Skill skill : skills) {
            if (skill != null) {
                addSkill(skill);
            }
        }
    }

    public ArrayList<Certification> getCertifications() {
        return new ArrayList<>(certifications);
    }

    public void setCertifications(ArrayList<Certification> certifications) {
        this.certifications = new ArrayList<>();
        if (certifications == null) {
            return;
        }
        for (Certification certification : certifications) {
            if (certification != null) {
                addCertification(certification);
            }
        }
    }

    public void addEducation(Education education) {
        if (education == null) {
            throw new IllegalArgumentException("Education entry cannot be null.");
        }
        for (Education existing : educationList) {
            if (existing.getDegree().equalsIgnoreCase(education.getDegree())
                    && existing.getYear() == education.getYear()) {
                return;
            }
        }
        educationList.add(education);
    }

    public void addSkill(Skill skill) {
        if (skill == null || isBlank(skill.getName())) {
            throw new IllegalArgumentException("Skill cannot be null or empty.");
        }
        if (hasSkill(skill.getName())) {
            return;
        }
        skills.add(skill);
    }

    public void addSkill(String skillName) {
        if (isBlank(skillName)) {
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
        }
        addSkill(new Skill(skillName));
    }

    public void addCertification(Certification certification) {
        if (certification == null || isBlank(certification.getTitle())) {
            throw new IllegalArgumentException("Certification cannot be null or empty.");
        }
        if (hasCertification(certification.getTitle())) {
            return;
        }
        certifications.add(certification);
    }

    public void addCertification(String title) {
        if (isBlank(title)) {
            throw new IllegalArgumentException("Certification title cannot be null or empty.");
        }
        addCertification(new Certification(title));
    }

    public boolean hasSkill(String skillName) {
        if (isBlank(skillName)) {
            return false;
        }
        String target = skillName.trim();
        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCertification(String title) {
        if (isBlank(title)) {
            return false;
        }
        String target = title.trim();
        for (Certification cert : certifications) {
            if (cert.getTitle().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeSkill(String skillName) {
        if (isBlank(skillName)) {
            return false;
        }
        String target = skillName.trim();
        for (int i = 0; i < skills.size(); i++) {
            if (skills.get(i).getName().equalsIgnoreCase(target)) {
                skills.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeSkill(Skill skill) {
        return skill != null && removeSkill(skill.getName());
    }

    public boolean removeCertification(String title) {
        if (isBlank(title)) {
            return false;
        }
        String target = title.trim();
        for (int i = 0; i < certifications.size(); i++) {
            if (certifications.get(i).getTitle().equalsIgnoreCase(target)) {
                certifications.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeCertification(Certification certification) {
        return certification != null && removeCertification(certification.getTitle());
    }

    public boolean removeEducation(String degree) {
        if (isBlank(degree)) {
            return false;
        }
        String target = degree.trim();
        for (int i = 0; i < educationList.size(); i++) {
            if (educationList.get(i).getDegree().equalsIgnoreCase(target)) {
                educationList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayEducation() {
        printSection("Education", educationList);
    }

    public void displaySkills() {
        printSection("Skills", skills);
    }

    public void displayCertifications() {
        printSection("Certifications", certifications);
    }

    /**
     * Prints the student's details along with education, skills, and certifications.
     */
    public void show() {
        System.out.println("===== Student Profile =====");
        System.out.println(student);
        displayEducation();
        displaySkills();
        displayCertifications();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
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
