public class ApplicationHistoryService {public void show(int studentId)throws Exception{
    for(Application a:new ApplicationDAO().forStudent(studentId))System.out.println(a);
}
 }
