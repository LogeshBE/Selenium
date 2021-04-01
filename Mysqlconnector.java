package Database;

import java.sql.DriverManager;
import java.sql.ResultSet;

import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.Statement;

public class Mysqlconnector {
  @Test
  public void DBConnector() {

          String Drivername = "com.mysql.jdbc.Driver";
          String url="jdbc:mysql://localhost:3306/facebook";
          String dbuser="root";
          String dbpass="12345";
         String sql= "Select * from login ;";
         try 
         {
              Class.forName(Drivername);
              Connection con=DriverManager.getConnection(url,dbuser,dbpass);
              Statement st=con.createStatement();  
              ResultSet rs=st.executeQuery(sql);
              while(rs.next()) {
            	  System.out.println(rs.getString(1)+" --> "+rs.getString(2));
              }
              }
         catch(Exception e) {
        	 System.out.println(e);
        	 }
  }
}
