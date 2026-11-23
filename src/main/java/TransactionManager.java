import java.sql.*;

public class TransactionManager {
    public void commit(Connection c) throws SQLException {
        c.commit();
    }

    public void rollback(Connection c) throws SQLException {
        c.rollback();
    }
}
