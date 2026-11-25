import java.sql.Connection;
import java.sql.Statement;

/**
 * Verifies database connectivity and required tables before launching the menu.
 */
public class DatabaseInitializer {
    private static final String[] REQUIRED_TABLES = {
            "students", "companies", "jobs", "applications", "interviews"
    };

    /**
     * Checks that the configured MySQL database is reachable and initialized.
     *
     * @return true if connection succeeds and all core tables exist, false otherwise
     */
    public boolean ready() {
        try (Connection c = DBConnection.connect();
             Statement s = c.createStatement()) {
            for (String table : REQUIRED_TABLES) {
                s.executeQuery("SELECT 1 FROM " + table + " LIMIT 1");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Database not ready: " + e.getMessage());
            if (DatabaseConfig.password() == null) {
                System.out.println("Hint: DB_PASSWORD environment variable is not set.");
            }
            System.out.println("Configured URL: " + DatabaseConfig.url());
            System.out.println("Ensure MySQL is running and run database/careernest.sql.");
            return false;
        }
    }
}
