package kolokwium_mysql;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ola
 */
public class ResultsDatabase {
    
    String dbName="kolokwium";
    Statement st=null; 
    
         public void createResultsTable() throws SQLException {
            Connection conn=new DatabaseConnection().connect(dbName); 
            try {
                st=conn.createStatement();
                st.executeUpdate("Create table wyniki (NIU varchar(10) NOT NULL, wynik INT NOT NULL);"); 
                System.out.println("Utworzono tabele wyniki");

            } catch (SQLException ex) {
                System.out.println("Nie udało się utworzyć tabeli wyniki");
            }
            finally{
                 if (st != null)  st.close(); 
                 if (conn!=null)  conn.close();
            }
    }
        public void addResult(String niu, int wynik) throws SQLException{
        Connection conn=new DatabaseConnection().connect(dbName); 
            try {
                st=conn.createStatement();  
                st.executeUpdate("insert into wyniki values("+niu+","+wynik+");"); 
                System.out.println("Dodano wynik do wyniki");
            } catch (SQLException ex) {
                System.out.println("Nie udało się dodac wyniku do wyniki");
            }
            finally{
            if (st != null)  st.close(); 
            if (conn!=null)  conn.close();
            }
            
        } 
}
