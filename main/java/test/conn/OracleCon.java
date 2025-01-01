package test.conn;

import java.sql.*;

public class OracleCon {
    public static void main(String args[]) {
        try {

            String url ="jdbc:oracle:thin:@//oracle-hive.csfw1hkmlpgh.us-east-1.rds.amazonaws.com:1521/ORCL";
            String driver = "oracle.jdbc.OracleDriver";
            String user ="admin";
            String pass = "unraveldata";
//step1 load the driver class  
            Class.forName(driver);

//step2 create  the connection object  
            Connection con = DriverManager.getConnection(
                    url, user, pass);
            System.out.println(con);

//step3 create the statement object  
            Statement stmt = con.createStatement();
            System.out.println(stmt);

//step4 execute query  
            /*ResultSet rs = stmt.executeQuery("select * from emp");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
*/
//step5 close the connection object  
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}  