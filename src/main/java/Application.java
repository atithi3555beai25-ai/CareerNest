public class Application {
    private int id,studentId,jobId;
    private String status;

    public Application(int id,int studentId,int jobId,String status){
        this.id=id;
        this.studentId=studentId;
        this.jobId=jobId;
        this.status=status;
    }

    public int getId(){
        return id;
    }

    public int getStudentId(){
        return studentId;
    }

    public int getJobId(){
        return jobId;
    }

    public String getStatus(){
        return status;
    }

    public String toString(){
        return id+" | Student: "+studentId+" | Job: "+jobId+" | "+status;
    }
    
 }
