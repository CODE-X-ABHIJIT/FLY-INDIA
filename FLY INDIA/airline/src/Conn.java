import java.sql.*;

public class Conn {

    Connection c;
    static Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql:///airlinemanagementsystem","root","Abhijit@2003");
            s=c.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
