package com.hive;

import test.conn.HiveMetastoreConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HiveMetaStore2 {

    private static final long TABLE_BATCH_SIZE = 50000L;
    static Connection connection;

    public static final String QMARK = "?";
    private static final String VERSION = "4780";
    private static final String USER = "unravel";
    private static final String SEPARATOR = "&amp;";
    public static final String USER_AGENT_ENTRY = "UserAgentEntry=" + USER + "_" + VERSION;

    public static void main(String[] args) throws SQLException {


        String driver = "org.postgresql.Driver";
        String pass = "p@ssw0rd";
        String url = "jdbc:postgresql://sd46.unraveldata.com:7432/hive";
        String user = "hive";

        HiveMetastoreConnection hiveObject = new HiveMetastoreConnection(decorateMetastoreURl(url), driver, user, pass);
        connection = hiveObject.getSqlConnection();
        System.out.println(hiveObject.getUrl());
        System.out.println("connection is " + connection);
       // getAllTableInfo();
       // getTableStats("r");
    }


    public static void getAllTable(Connection connection) throws SQLException {
        long offset = 0;
        long limitSize = 10000;
        while (true) {
            String limitOFFSet = "SELECT d.\"DB_ID\", " +
                    "       d.\"NAME\" as dbname, " +
                    " t.\"TBL_NAME\" ," +
                    " t.\"TBL_ID\" " +
                    "FROM   \"TBLS\" t " +
                    "JOIN   \"DBS\" d " +
                    "ON     t.\"DB_ID\" = d.\"DB_ID\"  " +
                    "order by  t.\"TBL_ID\"  " +
                    " LIMIT " + limitSize + " OFFSET " + offset;

            limitOFFSet = limitOFFSet.replaceAll("\\\"", "").replaceAll("     "," ");
            PreparedStatement statement = connection.prepareStatement(limitOFFSet);
            try {
                ResultSet resultSet = statement.executeQuery();
                System.out.println();
                long count = 0;
                while (resultSet.next()) {

                    String dbId = resultSet.getString(1);
                    String dbName = resultSet.getString(2);
                    String tableName = resultSet.getString(3);
                    String tableID = resultSet.getString(4);
                    System.out.println("dbName:  " + dbName + "   AND dbId :" + dbId + " table Name -  " + tableName  + "---->  AND  table ID:  " + tableID);
                    count++;
                }
                if (count < limitSize) {
                    offset = offset + count;
                    System.out.println(offset);
                    break;
                }
                offset = offset + count;

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


    public static void getAllTableInfo() throws SQLException {


        String sqlText = "SELECT t.\"TBL_ID\", " +
                "       count(1) as p_count, " +
                "       d.\"NAME\" as dbname, " +
                "       t.\"TBL_NAME\", " +
                "       t.\"OWNER\", " +
                "       t.\"CREATE_TIME\", " +
                "       t.\"TBL_TYPE\", " +
                "       t.\"RETENTION\", " +
                "       p.\"PKEY_NAME\", " +
                "       s.\"LOCATION\", " +
                "       s.\"NUM_BUCKETS\", " +
                "       s.\"INPUT_FORMAT\", " +
                "       s.\"OUTPUT_FORMAT\", " +
                "       s.\"IS_COMPRESSED\" , " +
                "       cs.\"LAST_ANALYZED\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                "LEFT JOIN  \"SDS\" s " +
                "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                "LEFT JOIN   \"TAB_COL_STATS\" cs " +
                "ON     t.\"TBL_ID\" = cs.\"TBL_ID\" " +
                "LEFT JOIN \"PARTITION_KEYS\" p " +
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" " +
                " INNER JOIN \"PARTITIONS\" P1 " +
                "ON P1.\"TBL_ID\" = t.\"TBL_ID\" " +
                " GROUP BY t.\"TBL_ID\" ";
        //"ORDER BY  t.\"TBL_ID\"  " +
        // "LIMIT " + limitSize + " OFFSET " + offset;

        PreparedStatement statement = connection.prepareStatement(sqlText);
        try {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String tableID = resultSet.getString(1);
                String pcount = resultSet.getString(2);
                String dbName = resultSet.getString(3);
                System.out.println("tableID   "+tableID+ "  pcount   " + pcount );
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void getAllTableWithPartitions1() throws SQLException {
        long offset = 0;
        long limitSize = 10000;
        long count = 0;
        String sqlText1 = "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        S.\"SD_ID\", " +
                "        D.\"NAME\", " +
                "        T.\"TBL_NAME\", " +
                "        P.\"CREATE_TIME\", " +
                "        S.\"LOCATION\", " +
                "        S.\"NUM_BUCKETS\" " +
                "FROM    \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "INNER JOIN \"DBS\" D " +
                "ON T.\"DB_ID\" = D.\"DB_ID\" " +
                "LEFT OUTER JOIN \"SDS\" S " +
                "ON P.\"SD_ID\" = S.\"SD_ID\" " +
                "ORDER BY  p.\"PART_ID\"  " +
                "LIMIT " + limitSize + " OFFSET " + offset;

        if (true) {
            sqlText1 = sqlText1.replaceAll("\\\"", "");
        }
        PreparedStatement statement = connection.prepareStatement(sqlText1);
        try {
            ResultSet resultSet = statement.executeQuery();
            System.out.println();
            while (resultSet.next()) {
                count++;
            }
            System.out.println("count " + count);
            if (count < limitSize) {
                offset = offset + count;
                System.out.println(offset);
                //break;
            }
            offset = offset + limitSize;
            //  System.out.println("partiton count ->  " + count + "for db ->  " + dbIds);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void getAllTableWithPartitions() throws SQLException {
        long count = 0;
        String sqlText = "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        S.\"SD_ID\", " +
                "        D.\"NAME\", " +
                "        T.\"TBL_NAME\", " +
                "        P.\"CREATE_TIME\", " +
                "        S.\"LOCATION\", " +
                "        S.\"NUM_BUCKETS\" " +
                "FROM    \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "INNER JOIN \"DBS\" D " +
                "ON T.\"DB_ID\" = D.\"DB_ID\" " +
                "LEFT OUTER JOIN \"SDS\" S " +
                "ON P.\"SD_ID\" = S.\"SD_ID\" ";

        PreparedStatement statement = connection.prepareStatement(sqlText);
        try {
            ResultSet resultSet = statement.executeQuery();
            System.out.println();
            while (resultSet.next()) {
                count++;
            }
            System.out.println("count " + count);

            //  System.out.println("partiton count ->  " + count + "for db ->  " + dbIds);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void getAllTables() throws SQLException {

        String sqlText = "SELECT t.\"TBL_ID\", " +
                "       d.\"NAME\" as dbname, " +
                "       t.\"TBL_NAME\", " +
                "       t.\"OWNER\", " +
                "       t.\"CREATE_TIME\", " +
                "       t.\"TBL_TYPE\", " +
                "       t.\"RETENTION\", " +
                "       p.\"PKEY_NAME\", " +
                "       s.\"LOCATION\", " +
                "       s.\"NUM_BUCKETS\", " +
                "       s.\"INPUT_FORMAT\", " +
                "       s.\"OUTPUT_FORMAT\", " +
                "       s.\"IS_COMPRESSED\" , " +
                "       cs.\"LAST_ANALYZED\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                "LEFT JOIN  \"SDS\" s " +
                "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                "LEFT JOIN   \"TAB_COL_STATS\" cs " +
                "ON     t.\"TBL_ID\" = cs.\"TBL_ID\" " +
                "LEFT JOIN \"PARTITION_KEYS\" p " +
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" ";
        PreparedStatement statement = connection.prepareStatement(sqlText);
        try {
            ResultSet resultSet = statement.executeQuery();
            long count = 0;
            while (resultSet.next()) {

                String dbId = resultSet.getString(1);
                String dbName = resultSet.getString(2);
                String tableName = resultSet.getString(3);
                String tableID = resultSet.getString(4);
                // System.out.println("dbName:  " + dbName + "   AND dbId :" + dbId + " table Name -  " + tableName  + "---->  AND  table ID:  " + tableID);
                count++;
            }
            System.out.println("table count " + count);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static long tableCount() throws SQLException {
        String tableCount = "SELECT count(1) as table_cnt " +
                "FROM   \"TBLS\"  ";
        PreparedStatement statement = connection.prepareStatement(tableCount);
        try {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println("table count" + resultSet.getString(1));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0L;
    }

    public static long paCount() throws SQLException {
        String partitioncount = "SELECT count(1) partition_count " +
                "FROM    \"PARTITIONS\" ";
        PreparedStatement statement = connection.prepareStatement(partitioncount);
        try {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println("Part count" + resultSet.getString(1));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0L;
    }

    public static void getTableStats(String dbIds) {
        String sqlText = "SELECT t.\"TBL_ID\", " +
                "       cs.\"LAST_ANALYZED\" " +
                "FROM \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                "LEFT JOIN   \"TAB_COL_STATS\" cs " +
                "ON     t.\"TBL_ID\" = cs.\"TBL_ID\" " +
                "where  t.\"DB_ID\"  IN ( " + dbIds + " )";

        String partitionIdTablePathQuery = "SELECT  P.\"PART_ID\", " +
                "        S.\"LOCATION\" " +
                "FROM   \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "INNER JOIN  \"SDS\" S " +
                "ON  T.\"SD_ID\" = S.\"SD_ID\" " +
                "ORDER BY  P.\"PART_ID\" ";

        System.out.println(partitionIdTablePathQuery);


    }


    public static long getAllPartitions(String dbIds) throws SQLException {
        long count = 0;

        String sqlText = "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        S.\"SD_ID\", " +
                "        D.\"NAME\", " +
                "        T.\"TBL_NAME\", " +
                "        P.\"CREATE_TIME\", " +
                "        S.\"LOCATION\", " +
                "        S.\"NUM_BUCKETS\" " +
                "FROM    \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "INNER JOIN \"DBS\" D " +
                "ON T.\"DB_ID\" = D.\"DB_ID\" " +
                "LEFT OUTER JOIN \"SDS\" S " +
                "ON P.\"SD_ID\" = S.\"SD_ID\"" +
                "WHERE  D.\"DB_ID\"  IN ( " + dbIds + " ) ";

        PreparedStatement statement = connection.prepareStatement(sqlText);
        try {
            ResultSet resultSet = statement.executeQuery();
            System.out.println();
            while (resultSet.next()) {
                count++;
            }
            System.out.println("count " + count);
            //  System.out.println("partiton count ->  " + count + "for db ->  " + dbIds);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    public static List<String> getDbIdStringList() {
        List<String> dbIdStringList = new ArrayList<>();
        Map<String, Long> dbIdTableCountMap = getDbIdTableCountMapping();
        long sum = 0;
        if (!dbIdTableCountMap.isEmpty()) {
            long totalTableCount = 0L;
            StringBuilder dbIds = new StringBuilder();
            for (String dbId : dbIdTableCountMap.keySet()) {
                sum = sum + dbIdTableCountMap.get(dbId);
                totalTableCount = totalTableCount + dbIdTableCountMap.get(dbId);
                System.out.println("dbId  " + dbId + " ------  table Count   " + dbIdTableCountMap.get(dbId));
                dbIds.append(dbId).append(",");
                if (totalTableCount > TABLE_BATCH_SIZE) {
                    dbIds.setLength(Math.max(dbIds.length() - 1, 0));
                    dbIdStringList.add(dbIds.toString());
                    totalTableCount = 0L;
                    dbIds = new StringBuilder();
                }
            }
            if (dbIds.length() > 0) {
                dbIds.setLength(Math.max(dbIds.length() - 1, 0));
                dbIdStringList.add(dbIds.toString());
            }

        }
        System.out.println("Total table count : " + sum);
        for (String s : dbIdStringList) {
            System.out.println(s);
        }
        return dbIdStringList;

    }

    public static Map<String, Long> getDbIdTableCountMapping() {
        Map<String, Long> dbIdTableCountMap = new TreeMap<>();


        String sqlTextGroupBy = "SELECT count(1) as table_cnt, d.\"DB_ID\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\"  " +
                "GROUP BY d.\"DB_ID\" " +
                "ORDER BY table_cnt desc";
        if (false) {
            sqlTextGroupBy = sqlTextGroupBy.replaceAll("\\\"", "");
        }


        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sqlTextGroupBy);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cnt = resultSet.getString(1);
                long tableCount = cnt != null ? Long.parseLong(cnt) : 0L;
                String dbId = resultSet.getString(2);
                dbIdTableCountMap.put(dbId, tableCount);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbIdTableCountMap;
    }

    public static String decorateMetastoreURl(String metastoreUrl) {

        if (true) {
            /*if (metastoreUrl.contains("oracle")) {
                metastoreUrl = metastoreUrl.concat(",").concat(USER_AGENT_ENTRY);
            }
           else*/
            if (metastoreUrl.contains(QMARK)) {
                metastoreUrl = metastoreUrl.concat(SEPARATOR).concat(USER_AGENT_ENTRY);
            } else {
                metastoreUrl = metastoreUrl.concat(QMARK).concat(USER_AGENT_ENTRY);
            }
        }
        System.out.println(metastoreUrl);
        return metastoreUrl;

    }

}
