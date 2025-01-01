package test.presto.govind;

import java.sql.*;
import com.facebook.presto.jdbc.PrestoDriver; 

//import presto jdbc driver packages here.  
public class PrestoJdbcSample {  
   public static void main(String[] args) {  
      Connection connection = null; 
      Statement statement = null;  
      try {
         http://35.170.50.213:8080/ui/
         Class.forName("com.facebook.presto.jdbc.PrestoDriver");  
         connection = DriverManager.getConnection("jdbc:presto://35.170.50.213:8080/mysql/tutorials", "admin", "");
         
         //connect mysql server tutorials database here 
         statement = connection.createStatement(); 
         String sql;  
         sql = "select count(*) from catalog_name.information_schema.tables";
        
         //select mysql table author table two columns  
         ResultSet resultSet = statement.executeQuery(sql);  
         while(resultSet.next()){  
            int id  = resultSet.getInt("auth_id"); 
            String name = resultSet.getString("auth_name");
            System.out.print("ID: " + id + ";\nName: " + name + "\n"); 
         }  
         
         resultSet.close(); 
         statement.close(); 
         connection.close(); 
         
      } catch(Exception sqlException){
         sqlException.printStackTrace();
      }
   } 
}