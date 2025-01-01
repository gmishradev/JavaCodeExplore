package com.hive;

import test.conn.HiveMetastoreConnection;

import java.sql.*;
import java.util.Map;

public class QueryExecutor {


    public static void main(String[] args) {
       // String url = "jdbc:sqlserver://localhost:1433;databaseName=mydatabase;user=myuser;password=mypassword;";
        // Replace the above connection string with the appropriate one for your SQL Server database

        // For Oracle, use the following connection string instead:
        // String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // Replace the above connection string with the appropriate one for your Oracle database
        String driver ="org.postgresql.Driver";
        String pass= "p@ssw0rd";
        String url="jdbc:postgresql://sd46.unraveldata.com:7432/hive";
        String user ="hive";

        HiveMetastoreConnection hiveObject = new HiveMetastoreConnection(url, driver, user, pass);
        //Connection connection = hiveObject.getSqlConnection();
        String query = "SELECT t.TBL_ID, d.NAME AS dbname, t.TBL_NAME, t.OWNER," +
                " t.CREATE_TIME, t.TBL_TYPE, t.RETENTION, p.PKEY_NAME, s.LOCATION, " +
                "s.NUM_BUCKETS, s.INPUT_FORMAT, s.OUTPUT_FORMAT, s.IS_COMPRESSED," +
                " cs.LAST_ANALYZED " +
                "FROM TBLS t " +
                "JOIN DBS d ON t.DB_ID = d.DB_ID " +
                "LEFT JOIN SDS s ON t.SD_ID = s.SD_ID " +
                "LEFT JOIN TAB_COL_STATS cs ON t.TBL_ID = cs.TBL_ID " +
                "LEFT JOIN PARTITION_KEYS p ON t.TBL_ID = p.TBL_ID " +
                "ORDER BY t.TBL_ID " +
                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int limit = 10; // Replace with the desired limit value
        int offset = 0; // Replace with the desired offset value

        try (Connection conn =  hiveObject.getSqlConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, offset);
            stmt.setInt(2, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int tblId = rs.getInt("TBL_ID");
                String dbName = rs.getString("dbname");
                String tblName = rs.getString("TBL_NAME");
                String owner = rs.getString("OWNER");
                Timestamp createTime = rs.getTimestamp("CREATE_TIME");
                String tblType = rs.getString("TBL_TYPE");
                int retention = rs.getInt("RETENTION");
                String pkeyName = rs.getString("PKEY_NAME");
                String location = rs.getString("LOCATION");
                int numBuckets = rs.getInt("NUM_BUCKETS");
                String inputFormat = rs.getString("INPUT_FORMAT");
                String outputFormat = rs.getString("OUTPUT_FORMAT");
                boolean isCompressed = rs.getBoolean("IS_COMPRESSED");
                Timestamp lastAnalyzed = rs.getTimestamp("LAST_ANALYZED");

                // Do something with the retrieved data
                System.out.printf("%d, %s, %s, %s, %s, %s, %d, %s, %s, %d, %s, %s, %b, %s\n",
                        tblId, dbName, tblName, owner, createTime, tblType, retention, pkeyName,
                        location, numBuckets, inputFormat, outputFormat, isCompressed, lastAnalyzed);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void partition() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=mydatabase;user=myuser;password=mypassword;";
        // Replace the above connection string with the appropriate one for your SQL Server database

        // For Oracle, use the following connection string instead:
        // String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // Replace the above connection string with the appropriate one for your Oracle database

        String query = "SELECT T.TBL_ID, P.PART_ID, S.SD_ID, D.NAME, T.TBL_NAME, P.CREATE_TIME, S.LOCATION, S.NUM_BUCKETS " +
                "FROM PARTITIONS P " +
                "INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID " +
                "INNER JOIN DBS D ON T.DB_ID = D.DB_ID " +
                "LEFT OUTER JOIN SDS S ON P.SD_ID = S.SD_ID " +
                "ORDER BY P.PART_ID " +
                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int limit = 10; // Replace with the desired limit value
        int offset = 0; // Replace with the desired offset value

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, offset);
            stmt.setInt(2, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int tblId = rs.getInt("TBL_ID");
                int partId = rs.getInt("PART_ID");
                int sdId = rs.getInt("SD_ID");
                String name = rs.getString("NAME");
                String tblName = rs.getString("TBL_NAME");
                Timestamp createTime = rs.getTimestamp("CREATE_TIME");
                String location = rs.getString("LOCATION");
                int numBuckets = rs.getInt("NUM_BUCKETS");

                // Do something with the retrieved data
                System.out.printf("%d, %d, %d, %s, %s, %s, %s, %d\n",
                        tblId, partId, sdId, name, tblName, createTime, location, numBuckets);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String tableQueryMSAndOracle(int limit, long offset) {

        String tableQuery = "SELECT t.TBL_ID, d.NAME AS dbname, t.TBL_NAME, t.OWNER, t.CREATE_TIME, t.TBL_TYPE, t.RETENTION, "
                + "p.PKEY_NAME, s.LOCATION, s.NUM_BUCKETS, s.INPUT_FORMAT, s.OUTPUT_FORMAT, s.IS_COMPRESSED, cs.LAST_ANALYZED"
                + "FROM TBLS t"
                + "JOIN DBS d ON t.DB_ID = d.DB_ID "
                + "LEFT JOIN SDS s ON t.SD_ID = s.SD_ID "
                + "LEFT JOIN TAB_COL_STATS cs ON t.TBL_ID = cs.TBL_ID "
                + "LEFT JOIN PARTITION_KEYS p ON t.TBL_ID = p.TBL_ID "
                + "ORDER BY t.TBL_ID "
                + "OFFSET " + offset + " ROWS "
                + "FETCH NEXT " + limit + " ROWS ONLY ";  // " LIMIT " + limit + " OFFSET " + offset;
        return tableQuery;
    }

    private String partitionQueryMSAndOracle(int limit, long offset) {

        String partitionQuery = "SELECT T.TBL_ID, P.PART_ID, S.SD_ID, D.NAME, T.TBL_NAME, P.CREATE_TIME, S.LOCATION,"
                + " S.NUM_BUCKETS"
                + "FROM PARTITIONS P "
                + "INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID "
                + "INNER JOIN DBS D ON T.DB_ID = D.DB_ID "
                + "LEFT OUTER JOIN SDS S ON P.SD_ID = S.SD_ID "
                + "ORDER BY P.PART_ID "
                + "OFFSET " + offset + " ROWS "
                + "FETCH NEXT " + limit + " ROWS ONLY ";
        return partitionQuery;
    }

    private String partitionIdANdTablePathQueryMSAndOracle(int limit, long offset) {

        String partitionIdANdTablePath = "SELECT P.PART_ID, s.LOCATION " +
                "FROM PARTITIONS P " +
                "INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID " +
                "LEFT JOIN SDS s ON T.SD_ID = s.SD_ID " +
                "ORDER BY P.PART_ID " +
                "OFFSET " + offset + " ROWS " +
                "FETCH NEXT " + limit + " ROWS ONLY ";
        return partitionIdANdTablePath;
    }

    private String tableIdPartitionCountQueryMSAndOracle(int limit, long offset) {

        String tableAllData ="SELECT t.TBL_NAME, t.TBL_TYPE, t.OWNER, t.CREATE_TIME, t.LAST_ACCESS_TIME, d.NAME AS DB_NAME, p.PARAM_VALUE AS COMMENT, " +
                "s.SD_INPUT_FORMAT AS INPUT_FORMAT, s.SD_OUTPUT_FORMAT AS OUTPUT_FORMAT, s.LOCATION, s.NUM_BUCKETS, s.IS_COMPRESSED, s.IS_STOREDASSUBDIRECTORIES," +
                " s.COMPRESSOR, s.BUCKET_COLS, s.SORT_COLS, s.PARAMETERS, GROUP_CONCAT(pk_col.COLUMN_NAME) AS PARTITION_KEYS, COUNT(p.PART_ID) AS PARTITION_COUNT " +
                "FROM TBLS t" +
                "JOIN DBS d ON t.DB_ID = d.DB_ID " +
                "JOIN SDS s ON t.SD_ID = s.SD_ID " +
                "LEFT JOIN (SELECT * FROM TABLE_PARAMS WHERE PARAM_KEY = 'comment') p ON t.TBL_ID = p.TBL_ID " +
                "LEFT JOIN PARTITION_KEYS pk ON t.TBL_ID = pk.TBL_ID " +
                "LEFT JOIN COLUMNS_V2 pk_col ON pk.PKEY_COMMENT = pk_col.COLUMN_COMMENT AND pk_col.CD_ID = t.TBL_ID " +
                "LEFT JOIN PARTITIONS p ON t.TBL_ID = p.TBL_ID " +
                "GROUP BY t.TBL_NAME, t.TBL_TYPE, t.OWNER, t.CREATE_TIME, t.LAST_ACCESS_TIME, d.NAME, p.PARAM_VALUE, s.SD_INPUT_FORMAT, " +
                "s.SD_OUTPUT_FORMAT, s.LOCATION, s.NUM_BUCKETS, s.IS_COMPRESSED, s.IS_STOREDASSUBDIRECTORIES, s.COMPRESSOR, s.BUCKET_COLS, s.SORT_COLS, s.PARAMETERS ";

       String tableAndPartitionKey = "SELECT t.tbl_id, GROUP_CONCAT(p.pkey_name)\n" +
               "FROM TBLS t \n" +
               "JOIN PARTITION_KEYS p \n" +
               "ON t.tbl_id = p.tbl_id\n" +
               "GROUP BY t.tbl_id";

        String tableIdPartitionCount = "SELECT COUNT(1) partition_count, T.TBL_ID " +
                "FROM PARTITIONS P " +
                "INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID " +
                "GROUP BY T.TBL_ID " +
                "ORDER BY T.TBL_ID " +
                "OFFSET " + offset + " ROWS " +
                "FETCH NEXT " + limit + " ROWS ONLY ";
        return tableIdPartitionCount;

    }


}