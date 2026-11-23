public class RegistrationService {
    private StudentManager manager = new StudentManager();

    public void register(Student s) throws Exception {
        if (!new UserValidator().validName(s.getName()))
            throw new IllegalArgumentException("Name required");
        manager.add(s);
    }
}
