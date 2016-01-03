package kolokwium_mysql;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ola
 */
public class AnswerDatabase {
    String dbName="kolokwium";
    Statement st=null; 
    
     public void createAnswerDatabase() throws SQLException {
         Connection conn=new DatabaseConnection().connect(dbName); 
        try {
            st=conn.createStatement();
            st.executeUpdate("Create table bazaOdpowiedzi (NIU varchar(10) NOT NULL, nrPytania INT NOT NULL, odpStudenta varchar(30)NOT NULL);"); 
            System.out.println("Utworzono tabele bazaOdpowiedzi");
            
        } catch (SQLException ex) {
            System.out.println("Nie udało się utworzyć tabeli bazaOdpowiedzi");
        }
        finally{
             if (st != null)  st.close(); 
             if (conn!=null)  conn.close();
        }
    }
        public void addAnswers(String niu, int nrPytania, String odpStudenta) throws SQLException{
        Connection conn=new DatabaseConnection().connect(dbName); 
            try {
                st=conn.createStatement();  
                st.executeUpdate("insert into bazaOdpowiedzi values("+niu+","+nrPytania+","+odpStudenta+");"); 
                System.out.println("Dodano odpowiedz do bazyOdpowiedzi");
            } catch (SQLException ex) {
                System.out.println("Nie udało się dodac odpowiedzi do bazaodpowiedzi");
            }
            finally{
            if (st != null)  st.close(); 
            if (conn!=null)  conn.close();
            }
            
        }    
}
