
import java.util.ArrayList;

public class ApplicationHistoryService {

    public void show(int studentId) throws Exception {

        ArrayList<Application> applications =
                new ApplicationRepository().forStudent(studentId);

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        System.out.println("\n--- Application History ---");

        for (Application a : applications) {
            System.out.println(a);
        }

        System.out.println("\nTotal Applications: "
                + applications.size());
    }
}
