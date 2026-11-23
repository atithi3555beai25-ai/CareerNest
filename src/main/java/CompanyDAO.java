import java.sql.*;
import java.util.*;

public class CompanyDAO {
    public void save(Company company) throws Exception {
        try (Connection c = DBConnection.connect();
                PreparedStatement p = c.prepareStatement("INSERT INTO companies(id,name) VALUES(?,?)")) {
            p.setInt(1, company.getId());
            p.setString(2, company.getName());
            p.executeUpdate();
        }
    }

    public ArrayList<Company> all() throws Exception {
        ArrayList<Company> list = new ArrayList<>();
        try (Connection c = DBConnection.connect();
                Statement s = c.createStatement();
                ResultSet r = s.executeQuery("SELECT * FROM companies")) {
            while (r.next())
                list.add(new Company(r.getInt("id"), r.getString("name")));
        }
        return list;
    }
}
