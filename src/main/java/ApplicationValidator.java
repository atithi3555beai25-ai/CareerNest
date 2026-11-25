public class ApplicationValidator {
    public boolean valid(Student student, Job job) {
        return student != null && job != null;
    }

    public void validateIds(int studentId, int jobId) {
        if (studentId <= 0 || jobId <= 0) {
            throw new IllegalArgumentException("Student and job IDs must be positive");
        }
    }

    public void checkDuplicate(int studentId, int jobId, ApplicationRepository dao)
            throws Exception {
        if (dao.exists(studentId, jobId)) {
            throw new DuplicateApplicationException("Already applied for this job");
        }
    }
}
