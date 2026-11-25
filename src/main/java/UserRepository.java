public class UserRepository {
    public Student findStudent(int id) throws Exception {
        return new StudentRepository().find(id);
    }
}
