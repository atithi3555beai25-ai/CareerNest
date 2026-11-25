public class ApplicationService {
    public void apply(int studentId, int jobId) throws Exception {
        ApplicationValidator validator = new ApplicationValidator();
        validator.validateIds(studentId, jobId);

        Student student = new StudentRepository().find(studentId);
        Job job = new JobRepository().find(jobId);
        if (!validator.valid(student, job)) {
            throw new IllegalArgumentException("Student or job not found");
        }

        EligibilityResult result = new EligibilityService().check(student, job);
        if (!result.isEligible()) {
            throw new IneligibleStudentException(result.getMessage());
        }

        ApplicationRepository dao = new ApplicationRepository();
        validator.checkDuplicate(studentId, jobId, dao);
        dao.save(studentId, jobId);
        System.out.println("Application submitted successfully");
    }
}
