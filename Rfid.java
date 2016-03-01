/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Pratik
 */


public class Rfid {
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException ce)
        {
        System.out.println("driver is loaded");
        }
        try
        {
              Connection con;
        con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
        System.out.println("Connection to the database is done.");
        Statement s=con.createStatement();
        }
        catch(SQLException e)
        {
            System.out.println("Error in connection");
        }
        // TODO code application logic here
        
     
    }
    
}
