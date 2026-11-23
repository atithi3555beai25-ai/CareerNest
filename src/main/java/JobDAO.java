import java.sql.*;
import java.util.*;

public class JobDAO {
    public void save(Job j) throws Exception {
        try (Connection c = DBConnection.connect();
                PreparedStatement p = c.prepareStatement(
                        "INSERT INTO jobs(id,company_id,title,branch,min_cgpa,max_backlogs) VALUES(?,?,?,?,?,?)")) {
            p.setInt(1, j.getId());
            p.setInt(2, j.getCompanyId());
            p.setString(3, j.getTitle());
            p.setString(4, j.getBranch());
            p.setDouble(5, j.getMinCgpa());
            p.setInt(6, j.getMaxBacklogs());
            p.executeUpdate();
        }
    }

    public Job find(int id) throws Exception {
        try (Connection c = DBConnection.connect();
                PreparedStatement p = c.prepareStatement("SELECT * FROM jobs WHERE id=?")) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                if (r.next())
                    return read(r);
            }
        }
        return null;
    }

    public ArrayList<Job> all() throws Exception {
        ArrayList<Job> list = new ArrayList<>();
        try (Connection c = DBConnection.connect();
                Statement s = c.createStatement();
                ResultSet r = s.executeQuery("SELECT * FROM jobs")) {
            while (r.next())
                list.add(read(r));
        }
        return list;
    }

    private Job read(ResultSet r) throws SQLException {
        return new Job(r.getInt("id"), r.getInt("company_id"), r.getString("title"), r.getString("branch"),
                r.getDouble("min_cgpa"), r.getInt("max_backlogs"));
    }
}
