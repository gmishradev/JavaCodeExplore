package test.presto.govind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PrestoJDBCExample {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        tables("hive");
        /**
         *
         * SQL Commands for getting table Information for trino.
         * select catalog_name from system.metadata.catalogs;
         * Sum the count from each catalog
         * select count(*) from catalog_name.information_schema.tables;
         * https://prestodb.io/docs/current/connector/system.html
         * Hitdocs - intops11 root/unraveldata
         * http://54.158.193.127:8080/ or
         * http://54.158.193.127:8112/
         * this is the presto server on which we run queries
         */
        // JDBC connection parameters
        String driver = "com.facebook.presto.jdbc.PrestoDriver";
        String url = "jdbc:presto://3.81.34.33/:8080/hive/presto_metadata_files";
        String user = "jdbc";
        String password = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

    }

    public static void tables(String catalogName) throws SQLException {
        //Class.forName("io.trino.jdbc.TrinoDriver");
        String url = "jdbc:trino://44.219.183.188:8080";
        String username = "jdbc";
        String password = "";
        String query2 = "SELECT * FROM " + catalogName + ".information_schema.tables";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query2);
        ResultSetMetaData metaData = resultSet.getMetaData();
        Integer columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        int c = 0;
        while (resultSet.next()) {
            // Retrieve data from each row
            String tableCatalogName = resultSet.getString("table_catalog");
            String tableSchema = resultSet.getString("table_schema");
            String tableName = resultSet.getString("table_name");
            String tableType = resultSet.getString("table_type");
            c++;
            System.out.println(String.format("Table Catalog: %s, Table Schema: %s, Table Name: %s, Table Type: %s",
                    tableCatalogName, tableSchema, tableName, tableType));

        }
        System.out.println("done ------->>>");
    }
}