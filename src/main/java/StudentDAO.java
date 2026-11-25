import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for CRUD operations on the students table using JDBC.
 */
public class StudentDAO {

    public void save(Student s) throws Exception {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        String sql = "INSERT INTO students(id, name, branch, cgpa, backlogs) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getBranch());
            ps.setDouble(4, s.getCgpa());
            ps.setInt(5, s.getBacklogs());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw wrapSqlException("saving student", e);
        }
    }

    public Student find(int id) throws Exception {
        if (id <= 0) {
            return null;
        }

        String sql = "SELECT id, name, branch, cgpa, backlogs FROM students WHERE id=?";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return read(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            throw wrapSqlException("finding student", e);
        }
    }

    public boolean exists(int id) throws Exception {
        if (id <= 0) {
            return false;
        }

        String sql = "SELECT 1 FROM students WHERE id=?";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw wrapSqlException("checking student existence", e);
        }
    }

    public ArrayList<Student> all() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, branch, cgpa, backlogs FROM students ORDER BY id";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(read(rs));
            }
            return students;
        } catch (SQLException e) {
            throw wrapSqlException("listing students", e);
        }
    }

    public ArrayList<Student> findByBranch(String branch) throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        if (branch == null || branch.trim().isEmpty()) {
            return students;
        }

        String sql = "SELECT id, name, branch, cgpa, backlogs FROM students WHERE LOWER(branch) = LOWER(?) ORDER BY id";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, branch.trim());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(read(rs));
                }
            }
            return students;
        } catch (SQLException e) {
            throw wrapSqlException("filtering students by branch", e);
        }
    }

    public boolean update(Student s) throws Exception {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        String sql = "UPDATE students SET name=?, branch=?, cgpa=?, backlogs=? WHERE id=?";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getBranch());
            ps.setDouble(3, s.getCgpa());
            ps.setInt(4, s.getBacklogs());
            ps.setInt(5, s.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw wrapSqlException("updating student", e);
        }
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            return false;
        }

        String sql = "DELETE FROM students WHERE id=?";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw wrapSqlException("deleting student", e);
        }
    }

    private Student read(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("branch"),
                rs.getDouble("cgpa"),
                rs.getInt("backlogs"));
    }

    private DatabaseException wrapSqlException(String action, SQLException e) {
        String state = e.getSQLState();
        if (state != null && state.startsWith("23")) {
            return new DatabaseException("Constraint error while " + action + ": " + e.getMessage());
        }
        if (state != null && state.startsWith("08")) {
            return new DatabaseException("Connection error while " + action + ": " + e.getMessage());
        }
        if (state != null && state.startsWith("42")) {
            return new DatabaseException("Schema or table error while " + action + ": " + e.getMessage());
        }
        return new DatabaseException("Database error while " + action + ": " + e.getMessage());
    }
}
