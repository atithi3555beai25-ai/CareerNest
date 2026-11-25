import java.sql.SQLException;

/**
 * Formats and prints user-friendly error messages for application exceptions.
 */
public class ErrorHandler {

    /**
     * Displays a contextual error message for the given exception.
     *
     * @param e exception caught during execution
     */
    public void show(Exception e) {
        if (e == null) {
            System.out.println("Error: An unknown error occurred.");
            return;
        }

        if (e instanceof IllegalArgumentException) {
            System.out.println("Validation Error: " + e.getMessage());
        } else if (e instanceof DuplicateApplicationException) {
            System.out.println("Duplicate Entry: " + e.getMessage());
        } else if (e instanceof IneligibleStudentException) {
            System.out.println("Eligibility Error: " + e.getMessage());
        } else if (e instanceof DatabaseException) {
            System.out.println("Database Error: " + e.getMessage());
        } else if (e instanceof SQLException sqlEx) {
            System.out.println("Database Error: " + describeSqlError(sqlEx));
        } else {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String describeSqlError(SQLException sqlEx) {
        String state = sqlEx.getSQLState();
        if (state != null && state.startsWith("23")) {
            return "A record with this ID or unique value already exists (" + sqlEx.getMessage() + ").";
        }
        if (state != null && state.startsWith("08")) {
            return "Unable to connect to MySQL. Check DB_PASSWORD and ensure MySQL is running.";
        }
        return sqlEx.getMessage();
    }
}
