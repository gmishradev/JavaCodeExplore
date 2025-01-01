package com.hive;

import test.conn.HiveMetastoreConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HiveMetaStore {

    private static final long TABLE_BATCH_SIZE = 50000L;
    static Connection connection;

    public static final String QMARK = "?";
    private static final String VERSION = "4780";
    private static final String USER = "unravel";
    private static final String SEPARATOR = "&amp;";
    public static final String USER_AGENT_ENTRY = "UserAgentEntry=" + USER + "_" + VERSION;

    public static void main(String[] args) throws SQLException {
       /* javax.jdo.option.ConnectionURL=jdbc:postgresql://sd330.unraveldata.com:7432/hive??useSSL=true
        javax.jdo.option.ConnectionDriverName=org.postgresql.Driver
        javax.jdo.option.ConnectionUserName=hive
        javax.jdo.option.ConnectionPassword=AES(b3811fcc8f09d63d568899f56098203cDl3jRzy+j0UHLTmWd9yq1f0+OO6cooiivgDpJUkXVdU=)*/

        /*String url = "jdbc:postgresql://sd330.unraveldata.com:7432/hive?useSSL=true";
        String driver = "org.postgresql.Driver";
        String user = "hive";
        String pass = "p@ssw0rd";*/

       /*  String driver ="com.mysql.cj.jdbc.Driver";
        String pass= "testing@123";
        String url="jdbc:mysql://external-hive-mysql.mysql.database.azure.com";
        String user ="unravel";
*/

       /* externalhivemetastore.database.windows.net -->  server name
        5:03
        jdbc:sqlserver://externalhivemetastore.database.windows.net:1433;database=external_hive_metastore;
        user=unravel@externalhivemetastore;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
        */

       /* String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String pass= "testing@123";
        String url="jdbc:sqlserver://externalhivemetastore.database.windows.net:1433;database=external_hive_metastore";
        String user ="unravel";*/

        /**
         * jdbc:oracle:thin:@//oracle-external-hive-metastore.csfw1hkmlpgh.us-east-1.rds.amazonaws.com:1521/ORCL
         * user: unravel
         * passwd: testing123
         */


        String driver ="oracle.jdbc.driver.OracleDriver";
        String pass= "unraveldata";
        String url="jdbc:oracle:thin:@//test5.csfw1hkmlpgh.us-east-1.rds.amazonaws.com:1521/ORCL";
        String user ="admin";


        /*String driver ="oracle.jdbc.driver.OracleDriver";
        String pass= "testing123";
        String url="jdbc:oracle:thin:@//oracle-external-hive-metastore.csfw1hkmlpgh.us-east-1.rds.amazonaws.com:1521/ORCL";
        String user ="unravel";*/


       /* javax.jdo.option.ConnectionURL=jdbc:postgresql://plr3-01.unraveldata.com:7432/hive
        javax.jdo.option.ConnectionDriverName=org.postgresql.Driver
        javax.jdo.option.ConnectionUserName=hive
        javax.jdo.option.ConnectionPassword=AES(5282b463bea16c7a6f36877f49b98840fVCM8whU8P2MrNo1+vRjsrCYHaV0EGxumk1y1Mb66fI=)*/
/*
        String driver ="org.postgresql.Driver";
        String pass= "p@ssw0rd";
        String url="jdbc:postgresql://plr3-01.unraveldata.com:7432/hive?useSSL=true";
        String user ="hive";*/

      /* String driver ="org.postgresql.Driver";
        String pass= "sEWE7wCpX3";
        String url="jdbc:postgresql://tnode118.unraveldata.com:7432/hive1";
        String user ="hive1";*/
        /*
        javax.jdo.option.playground_hive.ConnectionURL=jdbc:postgresql://tnode118.unraveldata.com:7432/hive1
javax.jdo.option.playground_hive.ConnectionDriverName=org.postgresql.Driver
javax.jdo.option.playground_hive.ConnectionUserName=hive1
javax.jdo.option.playground_hive.ConnectionPassword=sEWE7wCpX3
         */



        /*String driver ="org.mariadb.jdbc.Driver";
        String pass="m7VIhyc-o0cW5UwF1n39L4Lu33mczokHD14CIToF";
        String url="jdbc:mariadb://consolidated-eastusc3-prod-metastore-1.mysql.database.azure.com:3306/organization3546172795237754?useSSL=true&amp,serverSslCert=/databricks/common/mysql-ssl-ca-cert.crt&amp;UserAgentEntry=unravel_4780";
        String user ="qU8xEg6RHGomm2PP@consolidated-eastusc3-prod-metastore-1";

        javax.jdo.option.ConnectionURL=jdbc:postgresql://citi01.unraveldata.com:7432/hive
javax.jdo.option.ConnectionDriverName=org.postgresql.Driver
javax.jdo.option.ConnectionUserName=hive
javax.jdo.option.ConnectionPassword=AES(1f926b0d261df8b935e11a4d079353bbwGbCyv6GR6ZrI+vL9qiKL0wCVOqDT33FIEW2klGP4CU=)
p@ssw0rd




*/

        String driver2 = "com.mysql.cj.jdbc.Driver";//"com.mysql.cj.jdbc.Driver";
        String pass2 = "Unravel@123";
        String url2 = "jdbc:mysql://sd538.unraveldata.com:3306/hive4";//"jdbc:mysql://sd538.unraveldata.com:3306/hive1";
        String user2 = "hive"; // mysql -u hive -p Unravel@123


        String driver1 = "org.postgresql.Driver";
        String pass1 = "p@ssw0rd";
        String url1 = "jdbc:postgresql://sd46.unraveldata.com:7432/hive";
        String user1 = "hive";


        String driver3 = "com.mysql.cj.jdbc.Driver";
        String pass3 = "Unravel@123";
        String url3= "jdbc:mysql://intops03.unraveldata.com:3306/test";
        String user3 = "root";

        String driver4 = "org.mariadb.jdbc.Driver";
        String pass4 = "Unravel@123";
        String url4= "jdbc:mariadb://intops05.unraveldata.com:3306/test";
        String user4 = "root";
/*
        javax.jdo.option.hive1.ConnectionDriverName=com.mysql.cj.jdbc.Driver
        javax.jdo.option.hive1.ConnectionPassword=Unravel@123
        javax.jdo.option.hive1.ConnectionURL=jdbc:mysql://intops03.unraveldata.com:3306/test
        javax.jdo.option.hive1.ConnectionUserName=root
        hive.metastore.hive1.cluster.ids=5c33f4c0-a184-4fc6-b759-45e45ba352ff
        */

/*
        String driver = "org.postgresql.Driver";
        String pass = "sEWE7wCpX3";
        String url = "jdbc:postgresql://tnode118.unraveldata.com:7432/hive1";
        String user = "hive1";*/

        HiveMetastoreConnection hiveObject = new HiveMetastoreConnection(url4, driver4, user4, pass4);
        connection = hiveObject.getSqlConnection();
        System.out.println(hiveObject.getUrl());
        System.out.println("connection is " + connection);
        getAllTable();
       // DropDb(connection);

        //1687737600000 to 1687795200001

       /* Date date = new Date();

        //Timestamp timestamp = new Timestamp(1687737600000);
        System.out.println(date);*/

        //System.out.println(getNonURIPath("hdfs://sd125.unraveldata.com:8020/warehouse/tablespace/managed/hive/database - 201.db/table - 100000"));
        //  List<String> list = getAllDbId();
        //getAllTable();
        //getAllTableInfo();

        /*long start = System.nanoTime();

            getAllTables();
        tableCount();
            getAllTableWithPartitions();
            paCount();*/


        //Map<String, Long> map1 = getTablePartitionsCount();
        //System.out.println(map1);
        //Map<String, String> map = getTablePartitionKey();

        // map.forEach((k, v) -> System.out.println(k + "->" + v));
        //  Map<String, String> map = getPartitionIdTablePathMap();
        // System.out.println(map);
        // System.out.println(System.nanoTime() - start);

        LocalDate localDate =LocalDate.now();

        System.out.println(localDate.atStartOfDay().toLocalDate());
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        System.out.println(currentDate);
        LocalDateTime startOfDay = currentDate.atStartOfDay(ZoneOffset.UTC).toLocalDateTime();
        System.out.println(startOfDay);

    }


    private static long getStartOfTheDayInEpoch() {
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        ZoneId.getAvailableZoneIds();
        LocalDateTime startOfDay = currentDate.atStartOfDay(ZoneOffset.UTC).toLocalDateTime();
        return startOfDay.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
    public static List<String> getAllDbId() throws SQLException {
        List<String> dbIds = new ArrayList<>();
        String sqlTextGroupBy = "SELECT count(1) , d.\"DB_ID\", " +
                "       d.\"NAME\" as dbname " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\"  " +
                "GROUP BY d.\"DB_ID\"  ";


        PreparedStatement statement = connection.prepareStatement(sqlTextGroupBy);
        try {
            ResultSet resultSet = statement.executeQuery();
            System.out.println();
            long count = 0;
            while (resultSet.next()) {
                String cnt = resultSet.getString(1);
                String dbId = resultSet.getString(2);
                String dbName = resultSet.getString(3);
                System.out.println("dbName:  " + dbName + "   AND dbId :" + dbId + " total table count -  " + cnt);
                count++;
                dbIds.add(dbId);

            }
            System.out.println("count " + count);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return dbIds;

    }

    public static void getAllTable() throws SQLException {
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

            if (true) {
                limitOFFSet = limitOFFSet.replaceAll("\\\"", "");
            }


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
        long offset = 0;
        long limitSize = 10000;
        while (true) {
            /*String sqlText = "SELECT t.\"TBL_ID\", " +
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
                    "       s.\"IS_COMPRESSED\"  " +
                    "FROM   \"TBLS\" t " +
                    "JOIN   \"DBS\" d " +
                    "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                    "LEFT JOIN \"PARTITION_KEYS\" p " +
                    "ON     t.\"TBL_ID\" = p.\"TBL_ID\" " +
                    "LEFT JOIN  \"SDS\" s " +
                    "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                    "ORDER BY  t.\"TBL_ID\"  " +
                    "LIMIT " + limitSize + " OFFSET " + offset;*/

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
                    "       s.\"IS_COMPRESSED\"  " +
                    "FROM   \"TBLS\" t " +
                    "JOIN   \"DBS\" d " +
                    "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                    "LEFT JOIN \"PARTITION_KEYS\" p " +
                    "ON     t.\"TBL_ID\" = p.\"TBL_ID\" " +
                    "LEFT JOIN  \"SDS\" s " +
                    "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                    "ORDER BY  t.\"TBL_ID\"  " +
                    "LIMIT " + limitSize + " OFFSET " + offset;

            PreparedStatement statement = connection.prepareStatement(sqlText);
            try {
                ResultSet resultSet = statement.executeQuery();
                System.out.println();
                long count = 0;
                while (resultSet.next()) {

                    String dbId = resultSet.getString(1);
                    String dbName = resultSet.getString(2);
                    String tableName = resultSet.getString(3);
                    String tableID = resultSet.getString(4);
                    // System.out.println("dbName:  " + dbName + "   AND dbId :" + dbId + " table Name -  " + tableName  + "---->  AND  table ID:  " + tableID);
                    count++;
                }
                System.out.println("count " + count);
                if (count < limitSize) {
                    offset = offset + count;
                    System.out.println(offset);
                    break;
                }
                offset = offset + limitSize;


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


    public static void getAllTableWithPartitions(String dbIds) throws SQLException {
        long offset = 0;
        long limitSize = 10000;
        long count = 0;
        while (true) {
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
                    "ON P.\"SD_ID\" = S.\"SD_ID\" " +
                    "WHERE  D.\"DB_ID\"  IN ( " + dbIds + " ) " +
                    "ORDER BY  t.\"TBL_ID\"  " +
                    "LIMIT " + limitSize + " OFFSET " + offset;

            PreparedStatement statement = connection.prepareStatement(sqlText);
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
                    break;
                }
                offset = offset + limitSize;
                //  System.out.println("partiton count ->  " + count + "for db ->  " + dbIds);

            } catch (Exception e) {
                e.printStackTrace();

            }
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

    public static void getAllTables(String dbIds) throws SQLException {


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
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" " +
                "WHERE  t.\"DB_ID\"  IN ( " + dbIds + " )";

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
                "ON P.\"SD_ID\" = S.\"SD_ID\"";

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

    public static Map<String, Long> getTablePartitionsCount() {
        int limitSize = 100000;
        int offset = 0;
        Map<String, Long> tableIdPartitionCount = new TreeMap<>();
        String partitionCountQuery = "SELECT count(1) partition_count, T.\"TBL_ID\"  " +
                "FROM   \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "GROUP BY T.\"TBL_ID\" " +
                "order by  t.\"TBL_ID\"  " +
                " LIMIT " + limitSize + " OFFSET " + offset;

        if (false) {
            partitionCountQuery = partitionCountQuery.replaceAll("\\\"", "");
        }


        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(partitionCountQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cnt = resultSet.getString(1);
                long pCount = cnt != null ? Long.parseLong(cnt) : 0L;

                String tableId = resultSet.getString(2);
                System.out.println("table id " + tableId + "   pcount " + pCount);
                tableIdPartitionCount.put(tableId, pCount);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableIdPartitionCount;

    }

    public static Map<String, String> getPartitionIdTablePathMap() {
        int limitSize = 100000;
        int offset = 0;
        Map<String, String> tableIdPartitionCount = new TreeMap<>();
        String partitionCountQuery = "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        s.\"LOCATION\" " +
                "FROM   \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "LEFT JOIN  \"SDS\" s " +
                "ON  t.\"SD_ID\" = s.\"SD_ID\" " +
                "ORDER BY  P.\"PART_ID\"  " +
                "LIMIT " + limitSize + " OFFSET " + offset;

        if (false) {
            partitionCountQuery = partitionCountQuery.replaceAll("\\\"", "");
        }


        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(partitionCountQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tableId = resultSet.getString(1);

                String partId = resultSet.getString(2);
                String tablePath = resultSet.getString(3);
                System.out.println(partId + " partId" + "  table id and tablePath " + tableId + "->>>" + tablePath);
                tableIdPartitionCount.put(partId, (tableId + "->>>" + tablePath));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableIdPartitionCount;

    }


    public static Map<String, String> getTablePartitionKey() {
        Map<String, String> map = new HashMap<>();

        String tablePartitionKeyMapQuery = "SELECT t.\"TBL_ID\", " +
                "       p.\"PKEY_NAME\" " +
                "FROM   \"TBLS\" t " +
                "LEFT JOIN \"PARTITION_KEYS\" p " +
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" ";


        if (false) {
            tablePartitionKeyMapQuery = tablePartitionKeyMapQuery.replaceAll("\\\"", "");
        }


        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(tablePartitionKeyMapQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tableId = resultSet.getString(1);
                String newKey = resultSet.getString(2);
                String pKey = map.get(tableId);
                pKey = getPKeyString(newKey, pKey);
                map.put(tableId, pKey);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static String getPKeyString(String newKey, String pKey) {
        if (pKey != null && !pKey.isEmpty()) {
            pKey = pKey + "," + newKey;
        } else {
            pKey = newKey;
        }
        return pKey;
    }


    public static void DropDb(Connection con) {
        String url = "jdbc:mysql://localhost/";
        String user = "root";
        String password = "password";
        //String[] databaseNames = {"hive1", "hive2", "hive3", "hive4", "hive5"};
        Statement stmt = null;
        String[] databaseNames = { "hive4"};

        try {

            stmt = con.createStatement();

           /* String query = "show databases";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String table = resultSet.getString(1);
                System.out.println(table);
            }*/

            for (String dbName : databaseNames) {
                String query = "DROP DATABASE " + dbName;
                stmt.executeUpdate(query);
                System.out.println("Database " + dbName + " drop successfully...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getNonURIPath(String path) {
        if (path == null) {
            return null;
        }

        // Fsimage does not have Namenode URL in path. But Hive Metastore returns Namenode URL in path for Tables
        // and Partitions. So need to remove it here so that Fsimage can update this entry based on only absolute paths.
        // There are various ways hdfs file paths can be specified.
        // a ) hdfs://namenode:port/some/path
        // b)  hdfs:///some/path
        // c)  hdfs://namnode_ha_service_name/some/path
        // d)  /some/path
        // The following gets rules only gets the path part of the URL.
        if (path.contains(":///")) {
            return path.substring(path.indexOf("://") + 3);
        } else {
            return path.replaceFirst("^.*//.*?/", "/");
        }
    }


}
