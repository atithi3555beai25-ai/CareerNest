import java.sql.*;

public class DatabaseInitializer {
    public boolean ready() {
        try (Connection c = DBConnection.connect(); Statement s = c.createStatement()) {
            s.executeQuery("SELECT id FROM students LIMIT 1");
            return true;
        } catch (Exception e) {
            System.out.println("Database not ready. Run database/careernest.sql. " + e.getMessage());
            return false;
        }
    }
}
