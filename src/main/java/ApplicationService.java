public class ApplicationService {

    public void apply(int studentId, int jobId) throws Exception {

        ApplicationValidator validator = new ApplicationValidator();

        validator.validateIds(studentId, jobId);

        Student student = new StudentRepository().find(studentId);
        Job job = new JobRepository().find(jobId);

        validator.validateRecords(student, job);

        ApplicationRepository repository = new ApplicationRepository();

        validator.checkDuplicate(studentId, jobId, repository);

        EligibilityResult result =
                new EligibilityService().check(student, job);

        if (!result.isEligible()) {
            throw new IneligibleStudentException(
                    result.getMessage());
        }

        repository.save(studentId, jobId);

        System.out.println("Application submitted successfully");
    }
}