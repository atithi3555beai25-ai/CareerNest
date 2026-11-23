import java.sql.*;
import java.util.*;

public class ApplicationDAO {
    public boolean exists(int studentId,int jobId)throws Exception{
        try(Connection c=DBConnection.connect();
        PreparedStatement p=c.prepareStatement("SELECT id FROM applications WHERE student_id=? AND job_id=?")){
            p.setInt(1,studentId);
            p.setInt(2,jobId);
            try(ResultSet r=p.executeQuery()){return r.next();

            }
        }
    }
    public void save(int studentId,int jobId)throws Exception{
        try(Connection c=DBConnection.connect();
        PreparedStatement p=c.prepareStatement("INSERT INTO applications(student_id,job_id,status) VALUES(?,?,'Applied')")){
            p.setInt(1,studentId);
            p.setInt(2,jobId);
            p.executeUpdate();}}public ArrayList<Application> forStudent(int studentId)throws Exception{ArrayList<Application> list=new ArrayList<>();
                try(Connection c=DBConnection.connect();PreparedStatement p=c.prepareStatement("SELECT * FROM applications WHERE student_id=?")){p.setInt(1,studentId);
                    try(ResultSet r=p.executeQuery()){while(r.next())list.add(read(r));
                    }
                }
                return list;
            }public void printAll()throws Exception{
                try(Connection c=DBConnection.connect();
                Statement s=c.createStatement();
                ResultSet r=s.executeQuery("SELECT * FROM applications")){
                    while(r.next())System.out.println(read(r));
                }
            }
            public void updateStatus(int id,String status)throws Exception{
                try(Connection c=DBConnection.connect();
                PreparedStatement p=c.prepareStatement("UPDATE applications SET status=? WHERE id=?")){
                    p.setString(1,status);
                    p.setInt(2,id);
                    if(p.executeUpdate()==0)System.out.println("Application not found");
                }
            }
            private Application read(ResultSet r)throws SQLException{
                return new Application(r.getInt("id"),r.getInt("student_id"),r.getInt("job_id"),r.getString("status"));
            }
        }
