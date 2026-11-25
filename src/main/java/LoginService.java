public class LoginService {
    public Student studentLogin(int id) throws Exception {
        return new StudentRepository().find(id);
    }
}
