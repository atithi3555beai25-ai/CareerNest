public class ApplicationService {public void apply(int studentId,int jobId)throws Exception{
    Student s=new StudentDAO().find(studentId);
    Job j=new JobDAO().find(jobId);
    if(!new ApplicationValidator().valid(s,j))throw new IllegalArgumentException("Student or job not found");

    EligibilityResult result=new EligibilityService().check(s,j);

    if(!result.isEligible())throw new IneligibleStudentException(result.getMessage());

    ApplicationDAO dao=new ApplicationDAO();

    if(dao.exists(studentId,jobId))throw new DuplicateApplicationException("Already applied for this job");

    dao.save(studentId,jobId);
    
    System.out.println("Application submitted successfully");
}
 }
