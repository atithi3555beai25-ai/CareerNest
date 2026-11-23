
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    public void save(Student s) throws Exception {

        Connection con = DBConnection.connect();

        String sql = "INSERT INTO students(id, name, branch, cgpa, backlogs) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getBranch());
            ps.setDouble(4, s.getCgpa());
            ps.setInt(5, s.getBacklogs());

            ps.executeUpdate();

            ps.close();
        } finally {
            con.close();
        }
    }

    public Student find(int id) throws Exception {

        Connection con = DBConnection.connect();

        try {
            String sql = "SELECT * FROM students WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            Student s = null;

            if (rs.next()) {
                s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getDouble("cgpa"),
                        rs.getInt("backlogs"));
            }

            rs.close();
            ps.close();

            return s;

        } finally {
            con.close();
        }
    }

    public ArrayList<Student> all() throws Exception {

        ArrayList<Student> students = new ArrayList<>();

        Connection con = DBConnection.connect();

        try {
            String sql = "SELECT * FROM students ORDER BY id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getDouble("cgpa"),
                        rs.getInt("backlogs"));

                students.add(s);
            }

            rs.close();
            st.close();

        } finally {
            con.close();
        }

        return students;
    }
}
