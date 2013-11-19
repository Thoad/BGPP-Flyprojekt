package planereservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Nicolai
 */
public class PlaneReservation {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestClass();
    }
}

class TestClass {
    private Connection con;
    private Statement std;
    
    public TestClass() {
        testThis();
    }
    
    public void testThis() {
        System.out.println("Test af DB");
        
        try {
            con = DriverManager.getConnection( "jdbc:derby://localhost:1527/PlaneResDB");
        } catch (SQLException err) {
            System.out.println("SQL fejl: " + err.getMessage());
        }
        
        try {
            std = con.createStatement();
            
            ResultSet results = std.executeQuery("select * from APP.TESTTABLE");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }
            System.out.println("\n----------------------------------------------------------------------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String planeName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(results.getInt(1) + "\t\t" + results.getString(2) + "\t\t" + results.getInt(3) + "\t\t\t" + 
                        results.getInt(4) + "\t\t" + results.getString(5) + "\t\t" + results.getString(6));
            }
            results.close();
            std.close();

            
        } catch (SQLException sqlExcept) {
            
        }
    }
}
