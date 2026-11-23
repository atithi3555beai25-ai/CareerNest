public class ProfileManager {
    public void showStudent(Student student) {
        if (student == null)
            System.out.println("Student not found");
        else
            new StudentProfile(student).show();
    }
}
