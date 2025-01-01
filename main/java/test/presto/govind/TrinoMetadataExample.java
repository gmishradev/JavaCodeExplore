package test.presto.govind;

import java.sql.*;
import java.util.Properties;

public class TrinoMetadataExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:trino://44.219.183.188:8080/";
        String catalog = "hive";
        String schema = "presto_metadata_files";
        String username = "jdbc";
        String password = "";
        Properties properties = new Properties();
        properties.setProperty("user", "jdbc");
        properties.setProperty("password", "");


        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            ResultSet catalogs = connection.getMetaData().getCatalogs();
            while (catalogs.next()) {
                String catalogName = catalogs.getString("TABLE_CAT");
                System.out.println( " Catalog Name : " +  catalogName);
               // tables(connection, catalogName);
                tableMetadata(catalogName,connection);
            }
            catalogs.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void tables(Connection connection, String catalogName) throws SQLException {
        ResultSet tables = connection.getMetaData().getTables(catalogName, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            String tableCatalog = tables.getString("TABLE_CAT");
            String tableSchema = tables.getString("TABLE_SCHEM");
            String tableName = tables.getString("TABLE_NAME");
            String tableType = tables.getString("TABLE_TYPE");
           /* String tableRemarks = tables.getString("REMARKS");
            String catalogType = tables.getString("TYPE_CAT");
            String schemaType = tables.getString("TYPE_SCHEM");
            String nameType = tables.getString("TYPE_NAME");
            String selfReferencingColName = tables.getString("SELF_REFERENCING_COL_NAME");
            String refGeneration = tables.getString("REF_GENERATION");*/
            System.out.println("Table Name : " + tableName);
        }
        tables.close();
    }

    private static void tableMetadata(String catalogName, Connection connection) throws SQLException {
        String query2= "SELECT * FROM "+ catalogName  +".information_schema.tables";
        Statement statement = connection.createStatement();
       // statement.execute(query1);
        ResultSet resultSet = statement.executeQuery(query2);
        ResultSetMetaData metaData = resultSet.getMetaData();
        Integer columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        int c=0;
        while (resultSet.next()) {
            // Retrieve data from each row

            /*
            hive - > table_catalog
            information_schema - > table_schema
            columns - > table_name
            BASE TABLE - > table_type
             */
            /*String id = resultSet.getString(metaData.getColumnLabel(1));
            System.out.println(id + " - > " + metaData.getColumnLabel(1));
            String id1 = resultSet.getString(metaData.getColumnLabel(2));
            System.out.println(id1 + " - > " + metaData.getColumnLabel(2));
            String id2 = resultSet.getString(metaData.getColumnLabel(3));
            System.out.println(id2 + " - > " + metaData.getColumnLabel(3));
            String id3 = resultSet.getString(metaData.getColumnLabel(4));
            System.out.println(id3 + " - > " + metaData.getColumnLabel(4));*/
            c++;

        }
        System.out.println( "Numeber of rows for   "+catalogName +"==== "+ c);
    }
}