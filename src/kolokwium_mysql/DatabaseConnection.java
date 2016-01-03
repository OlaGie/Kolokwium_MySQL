package kolokwium_mysql;

import com.mysql.jdbc.Connection;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Ola
 */
public class DatabaseConnection {
    private Connection connection;
    
      public Connection connect(String dbName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("DatabaseConnection failed: " + ex);
        }
                String url = ("jdbc:mysql://localhost:3306/"+dbName);
                String username="root";
        try{
            connection = (Connection) DriverManager.getConnection(url, username, "");
            //System.out.println("Database connected.");
        }
        catch (SQLException se){
            System.out.println("Database not found." + se);
        }
        return connection;
      }   
}
