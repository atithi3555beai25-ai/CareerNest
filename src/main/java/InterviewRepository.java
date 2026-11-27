
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InterviewRepository {

    public ArrayList<Interview> all() throws Exception {

        ArrayList<Interview> list = new ArrayList<>();

        for (String row : FileStorage.read("interviews")) {

            String[] a = row.split("\\|", -1);

            list.add(new Interview(
                Integer.parseInt(a[0]),
                a[1],
                a[2]
            ));
        }

        return list;
    }

    private void store(ArrayList<Interview> list) throws Exception {

        ArrayList<String> rows = new ArrayList<>();

        for (Interview item : list) {

            rows.add(
                "" + item.getApplicationId() + "|"
                + item.getDate() + "|"
                + item.getResult()
            );
        }

        FileStorage.write("interviews", rows);
    }

    public void save(Interview item) throws Exception {

        // 1. Check whether application exists
        if (new ApplicationRepository().find(item.getApplicationId()) == null) {
            throw new IllegalArgumentException("Application not found");
        }

        // 2. Validate interview date
        LocalDate interviewDate;

        try {

            interviewDate = LocalDate.parse(item.getDate());

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException(
                "Invalid date! Please enter date in YYYY-MM-DD format."
            );
        }

        // 3. Prevent past interview dates
        if (interviewDate.isBefore(LocalDate.now())) {

            throw new IllegalArgumentException(
                "Interview date cannot be in the past."
            );
        }

        // 4. Check existing interviews
        ArrayList<Interview> list = all();

        for (Interview existing : list) {

            if (existing.getApplicationId() == item.getApplicationId()
                    && existing.getDate().equals(item.getDate())) {

                throw new IllegalArgumentException(
                    "Interview already scheduled for this application on this date."
                );
            }
        }

        // 5. Validate result
        RecordValidator.text(item.getResult());

        // 6. Save interview
        list.add(item);

        store(list);
    }

    public void printAll() throws Exception {

        int id = 1;

        for (Interview item : all()) {

            System.out.println(
                id++ + " | Application: "
                + item.getApplicationId()
                + " | " + item.getDate()
                + " | " + item.getResult()
            );
        }
    }
}
