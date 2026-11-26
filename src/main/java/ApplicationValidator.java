public class ApplicationValidator {

    public boolean valid(Student student, Job job) {
        return student != null && job != null;
    }

    public void validateIds(int studentId, int jobId) {

        if (studentId <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive");
        }

        if (jobId <= 0) {
            throw new IllegalArgumentException(
                    "Job ID must be positive");
        }
    }

    public void validateRecords(Student student, Job job) {

        if (student == null) {
            throw new IllegalArgumentException(
                    "Student not found");
        }

        if (job == null) {
            throw new IllegalArgumentException(
                    "Job not found");
        }
    }

    public void checkDuplicate(int studentId, int jobId,
            ApplicationRepository repository) throws Exception {

        if (repository.exists(studentId, jobId)) {
            throw new DuplicateApplicationException(
                    "Already applied for this job");
        }
    }
}