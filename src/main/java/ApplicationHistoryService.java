import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationHistoryService {

    public void show(int studentId) throws Exception {

        ArrayList<Application> applications =
                new ApplicationRepository().forStudent(studentId);

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        HashMap<String, Integer> statusCount = new HashMap<>();

        System.out.println("\n--- Application History ---");

        for (Application a : applications) {

            System.out.println(a);

            String status = a.getStatus();

            if (statusCount.containsKey(status)) {
                statusCount.put(status, statusCount.get(status) + 1);
            } else {
                statusCount.put(status, 1);
            }
        }

        System.out.println("\n--- Application Summary ---");

        System.out.println("Total Applications: " + applications.size());

        System.out.println("Applied: " +
                statusCount.getOrDefault("APPLIED", 0));

        System.out.println("Shortlisted: " +
                statusCount.getOrDefault("SHORTLISTED", 0));

        System.out.println("Selected: " +
                statusCount.getOrDefault("SELECTED", 0));

        System.out.println("Rejected: " +
                statusCount.getOrDefault("REJECTED", 0));
    }
}