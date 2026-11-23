import java.sql.*;

public class InterviewDAO {
    public void save(Interview i) throws Exception {
        try (Connection c = DBConnection.connect();
                PreparedStatement p = c.prepareStatement(
                        "INSERT INTO interviews(application_id,interview_date,result) VALUES(?,?,?)")) {
            p.setInt(1, i.getApplicationId());
            p.setString(2, i.getDate());
            p.setString(3, i.getResult());
            p.executeUpdate();
        }
    }

    public void printAll() throws Exception {
        try (Connection c = DBConnection.connect();
                Statement s = c.createStatement();
                ResultSet r = s.executeQuery("SELECT * FROM interviews")) {
            while (r.next())
                System.out.println(r.getInt("id") + " | Application: " + r.getInt("application_id") + " | "
                        + r.getString("interview_date") + " | " + r.getString("result"));
        }
    }
}
