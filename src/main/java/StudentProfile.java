public class StudentProfile {
    private Student student;

    public StudentProfile(Student student) {
        this.student = student;
    }

    public void show() {
        System.out.println(student);
    }
}
