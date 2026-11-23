import java.sql.*;

public class DBConnection {
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.url(), DatabaseConfig.user(), DatabaseConfig.password());
    }
}
