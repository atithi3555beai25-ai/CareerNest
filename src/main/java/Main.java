
public class Main {

    public static void main(String[] args) {

        StorageInitializer db = new StorageInitializer();

        if (!db.ready()) {
            return;
        }

        MainMenu menu = new MainMenu();
        ErrorHandler errors = new ErrorHandler();

        while (true) {

            menu.show();

            int choice = InputHandler.number("Choice: ");

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                run(choice);
            } catch (Exception e) {
                errors.show(e);
            }

            // Wait before displaying menu again
            System.out.println();
            InputHandler.line("Press Enter to return to Main Menu...");
        }
    }

    public static void run(int choice) throws Exception {

        if (choice == 1) {

            int id = InputHandler.number("Student ID: ");
            String name = InputHandler.line("Name: ");
            String branch = InputHandler.line("Branch: ");
            double cgpa = InputHandler.decimal("CGPA (0-10): ");
            int backlogs = InputHandler.number("Backlogs: ");

            if (!new InputValidator().cgpa(cgpa) || backlogs < 0) {
                throw new IllegalArgumentException("Invalid CGPA or backlogs");
            }

            Student s = new Student(id, name, branch, cgpa, backlogs);
            new RegistrationService().register(s);

            System.out.println("Student saved successfully!");
        }

        else if (choice == 2) {

            System.out.println("===== Student List =====");
            new StudentManager().list();
        }

        else if (choice == 3) {

            int id = InputHandler.number("Company ID: ");
            String name = InputHandler.line("Company Name: ");

            Company c = new Company(id, name);
            new CompanyManager().add(c);

            System.out.println("Company saved successfully!");
        }

        else if (choice == 4) {

            System.out.println("===== Company List =====");
            new CompanyManager().listSorted();
        }

        else if (choice == 5) {

            int id = InputHandler.number("Job ID: ");
            int company = InputHandler.number("Company ID: ");
            String title = InputHandler.line("Job Title: ");
            String branch = InputHandler.line("Allowed Branch (or ALL): ");
            double cgpa = InputHandler.decimal("Minimum CGPA: ");
            int backlogs = InputHandler.number("Maximum Backlogs: ");

            Job job = new Job(id, company, title, branch, cgpa, backlogs);

            new JobManager().add(job);

            System.out.println("Job saved successfully!");
        }

        else if (choice == 6) {

            System.out.println("===== Job List =====");
            new JobManager().list();
        }

        else if (choice == 7) {

            int studentId = InputHandler.number("Student ID: ");
            int jobId = InputHandler.number("Job ID: ");

            Student student = new StudentRepository().find(studentId);
            Job job = new JobRepository().find(jobId);

            EligibilityResult result = new EligibilityService().check(student, job);

            System.out.println(result.getMessage());
        }

        else if (choice == 8) {

            int studentId = InputHandler.number("Student ID: ");
            int jobId = InputHandler.number("Job ID: ");

            new ApplicationService().apply(studentId, jobId);
        }

        else if (choice == 9) {

            int studentId = InputHandler.number("Student ID: ");

            System.out.println("===== Application History =====");

            new ApplicationHistoryService().show(studentId);
        }

        else if (choice == 10) {

            System.out.println("===== All Applications =====");

            new ApplicationRepository().printAll();
        }

        else if (choice == 11) {

            int id = InputHandler.number("Application ID: ");

            System.out.println("1. Shortlisted");
            System.out.println("2. Selected");
            System.out.println("3. Rejected");

            int status = InputHandler.number("Status: ");

            String value = "";

            if (status == 1) {
                value = ApplicationStatus.SHORTLISTED;
            } else if (status == 2) {
                value = ApplicationStatus.SELECTED;
            } else if (status == 3) {
                value = ApplicationStatus.REJECTED;
            } else {
                throw new IllegalArgumentException("Invalid status");
            }

            new ApplicationRepository().updateStatus(id, value);

            System.out.println("Application status updated!");
        }

        else if (choice == 12) {

            int applicationId = InputHandler.number("Application ID: ");
            String date = InputHandler.line("Interview Date (YYYY-MM-DD): ");

            new InterviewManager().schedule(applicationId, date);

            System.out.println("Interview saved successfully!");
        }

        else if (choice == 13) {

            System.out.println("===== Interview List =====");

            new InterviewManager().list();
        }

        else if (choice == 14) {

            String title = InputHandler.line("Search Job Title: ");

            new JobSearchService().search(title);
        }

        else {

            System.out.println("Invalid choice!");
        }
    }
}
