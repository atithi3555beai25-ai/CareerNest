public class UserDAO {
    public Student findStudent(int id) throws Exception {
        return new StudentDAO().find(id);
    }
}
