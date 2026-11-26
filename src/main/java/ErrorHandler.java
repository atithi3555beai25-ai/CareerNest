import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
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
        show(null, e);
    }

    /**
     * Displays a contextual error message with an optional operation label.
     *
     * @param context short description of the operation that failed (may be null)
     * @param e       exception caught during execution
     */
    public void show(String context, Exception e) {
        String prefix = (context != null && !context.trim().isEmpty())
                ? "[" + context.trim() + "] "
                : "";

        if (e == null) {
            System.out.println(prefix + "Error: An unknown error occurred.");
            return;
        }

        if (e instanceof NumberFormatException) {
            System.out.println(prefix + "Data Format Error: Invalid numeric value (" + e.getMessage() + ").");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println(prefix + "Validation Error: " + e.getMessage());
        } else if (e instanceof InvalidPathException) {
            System.out.println(prefix + "File Path Error: " + e.getMessage());
        } else if (e instanceof NoSuchFileException || e instanceof FileNotFoundException) {
            System.out.println(prefix + "File Not Found: " + e.getMessage());
        } else if (e instanceof IOException) {
            System.out.println(prefix + "File I/O Error: " + e.getMessage());
        } else if (e instanceof SecurityException) {
            System.out.println(prefix + "Permission Error: " + e.getMessage());
        } else if (e instanceof DuplicateApplicationException) {
            System.out.println(prefix + "Duplicate Entry: " + e.getMessage());
        } else if (e instanceof IneligibleStudentException) {
            System.out.println(prefix + "Eligibility Error: " + e.getMessage());
        } else if (e instanceof DatabaseException) {
            System.out.println(prefix + "Database Error: " + e.getMessage());
        } else if (e instanceof SQLException sqlEx) {
            System.out.println(prefix + "Database Error: " + describeSqlError(sqlEx));
        } else {
            System.out.println(prefix + "Error: " + e.getMessage());
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
