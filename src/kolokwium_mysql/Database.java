package kolokwium_mysql;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ola
 */
public class Database {
    String dbName=null;
    Statement st=null;       
    ResultSet rs=null;    

    public void createDatabase() throws SQLException {
        dbName="";
        Connection conn=new DatabaseConnection().connect(dbName); 
       
        try {
            st=conn.createStatement();
            st.executeUpdate("Create Database kolokwium;");
            System.out.println("Utworzono baze danych kolokwium");
            
        } catch (SQLException ex) {
            System.out.println("Nie udało się utworzyć bazy danych kolokwium");
        }
        finally{
             if (st != null)  st.close(); 
             if (conn!=null)  conn.close();
        }
    }
        public void createTable() throws SQLException {
        dbName="kolokwium";
        Connection conn=new DatabaseConnection().connect(dbName);

        try {
            st=conn.createStatement();  
            st.executeUpdate("Create table Pytania (id INT NOT NULL, Pytanie varchar(100) NOT NULL, odp1 varchar(30)NOT NULL,odp2 varchar(30)NOT NULL, odp3 varchar(30)NOT NULL,odp4 varchar(30)NOT NULL,odpPoprawna varchar(1) NOT NULL);"); 
            st.executeUpdate("insert into pytania values(1,\"Ile dni moze miec miesiac luty\",\"28\",\"29\",\"30\",\"dwa z powyzszych\",4);"); 
            st.executeUpdate("insert into pytania values(2,\"Jaka barwa powstanie z wymieszania zoltej i zielonej\",\"niebieski\",\"czerwony\",\"pomaranczowy\",\"fioletowy\",1)");
            st.executeUpdate("insert into pytania values(3,\"Jaki jeste skrot swiatowej organizacji zdrowia?\",\"NFZ\",\"WHO\",\"EU\",\"WWF\",2)"); 
        System.out.println("Utworzono tabele Pytania");
        } catch (SQLException ex) {
            System.out.println("Nie udało się utworzyć tabeli Pytania");
        }
        finally{
        if (st != null)  st.close(); 
        if (conn!=null)  conn.close();
        }
    }
        
        public String showQuestions(int nrPytania, int nrOdpowiedzi) throws SQLException{
        dbName="kolokwium";
        Connection conn=new DatabaseConnection().connect(dbName);
        
        String query = "select * from pytania where id=" +(nrPytania+1);
        try {
            //System.out.println("jestem w try");
             //System.out.println(query);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String pytanie = rs.getString("Pytanie");
                //System.out.println("Pytanie: "+pytanie);
                String odp1 = rs.getString("odp1");
                //System.out.println("Podp1: "+odp1);
                String odp2 = rs.getString("odp2");
                String odp3 = rs.getString("odp3");
                String odp4 = rs.getString("odp4");
                String odpPoprawna = rs.getString("odpPoprawna");
                
                        switch(nrOdpowiedzi){
                         case 0: return pytanie;
                         case 1: return odp1;
                         case 2: return odp2;
                         case 3: return odp3;
                         case 4: return odp4; 
                         case 5: return odpPoprawna;    
                     }
                 }
            
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
        if (st != null)  st.close(); 
        if (conn!=null)  conn.close();
        if (rs!=null)  rs.close();
        }
        //return "nie weszlo do while";
            return "";
        }

//    public static void main(String[] args) throws SQLException{
//         Database db=new Database();
//         db.createDatabase(); 
//    }   
}