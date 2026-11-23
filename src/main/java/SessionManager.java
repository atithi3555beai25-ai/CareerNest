public class SessionManager {
    private int studentId;

    public void login(int id) {
        studentId = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void logout() {
        studentId = 0;
    }
}
