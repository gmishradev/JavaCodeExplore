package test.conn;

import com.hive.HiveMetaStore2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

       /* String url ="jdbc:oracle:thin:@//oracle-hive.csfw1hkmlpgh.us-east-1.rds.amazonaws.com:1521/ORCL";
        String driver = "oracle.jdbc.OracleDriver"; //"oracle.jdbc.OracleDriver";
        String user ="admin";
        String pass = "unraveldata";
        //javax.jdo.option.ConnectionPassword=AES(c70c47dc424970b740fd41c64948618cQKtTmSpYnOjQXFvkjt3xkzbfWCnujl+aJ8S4nobLSeM=)
        HiveMetastoreConnection hiveMetastoreConnection = new HiveMetastoreConnection(url,driver,user,pass);
        System.out.println(hiveMetastoreConnection.getSqlConnection());*/

        String driver = "org.mariadb.jdbc.Driver";
        String pass = "m7VIhyc-o0cW5UwF1n39L4Lu33mczokHD14CIToF";
        String url = "jdbc:mariadb://consolidated-eastusc3-prod-metastore-1.mysql.database.azure.com:3306/organization3546172795237754?useSSL=true&amp;enabledSslProtocolSuites=TLSv1,TLSv1.1,TLSv1.2&amp;serverSslCert=/databricks/common/mysql-ssl-ca-cert.crt&amp;UserAgentEntry=unravel_4780";
        String user = "qU8xEg6RHGomm2PP@consolidated-eastusc3-prod-metastore-1";

       /* String url ="jdbc:mariadb://mdpartyyphlhsp.caj77bnxuhme.us-west-2.rds.amazonaws.com:3306/organization6520864603684058?useSSL=true&amp;enabledSslProtocolSuites=TLSv1,TLSv1.1,TLSv1.2&amp;serverSslCert=/Users/gmishra/Documents/Delta/delta/examples/Code/src/main/resources/global-bundle.pem";
        String driver = "org.mariadb.jdbc.Driver";
        String user ="cCMQoNNjqLKEjmY4";
        String pass = "CtdDiB7BITbfEK4nKPdzfaYgvMO1vSp5G2ZDyxUz";*/

/*
        hive.metastore.cluster.ids=default
        javax.jdo.option.ktkwspace2.ConnectionDriverName=org.mariadb.jdbc.Driver
        javax.jdo.option.ktkwspace2.ConnectionPassword=w2YdIcm6HJAoIsr_O2hNNytl5T4odf6JTVTQdmTL
        javax.jdo.option.ktkwspace2.ConnectionURL=jdbc:mariadb://consolidated-eastusc3-prod-metastore-0.mysql.database.azure.com:3306/organization1583126034795612?useSSL=true&amp;enabledSslProtocolSuites=TLSv1,TLSv1.1,TLSv1.2&amp;serverSslCert=/databricks/common/mysql-ssl-ca-cert.crt
        javax.jdo.option.ktkwspace2.ConnectionUserName=yFJSXQTGLLdO7l7l@consolidated-eastusc3-prod-metastore-0

 */
        String url2 ="jdbc:mariadb://consolidated-eastusc3-prod-metastore-0.mysql.database.azure.com:3306/organization1583126034795612?useSSL=true&amp;enabledSslProtocolSuites=TLSv1,TLSv1.1,TLSv1.2&amp;serverSslCert=/Users/gmishra/Documents/Delta/delta/examples/Code/src/main/resources/global-bundle.pem";
        String driver2 = "org.mariadb.jdbc.Driver";
        String user2 ="yFJSXQTGLLdO7l7l@consolidated-eastusc3-prod-metastore-0";
        String pass2 = "w2YdIcm6HJAoIsr_O2hNNytl5T4odf6JTVTQdmTL";


      /*  javax.jdo.option.AI-Workspace.ConnectionDriverName=org.mariadb.jdbc.Driver
        javax.jdo.option.AI-Workspace.ConnectionPassword=m7VIhyc-o0cW5UwF1n39L4Lu33mczokHD14CIToF
        javax.jdo.option.AI-Workspace.ConnectionURL=jdbc:mariadb://consolidated-eastusc3-prod-metastore-1.mysql.database.azure.com:3306/organization3546172795237754?useSSL=true&amp;enabledSslProtocolSuites=TLSv1,TLSv1.1,TLSv1.2&amp;serverSslCert=/databricks/common/mysql-ssl-ca-cert.crt
        javax.jdo.option.AI-Workspace.ConnectionUserName=qU8xEg6RHGomm2PP@consolidated-eastusc3-prod-metastore-1*/

/*
javax.jdo.option.govind.ConnectionURL=jdbc:postgresql://20.84.102.116:5432/hivemetastoredb
javax.jdo.option.govind.ConnectionDriverName=org.postgresql.Driver
javax.jdo.option.govind.ConnectionUserName=postgres
javax.jdo.option.govind.ConnectionPassword=postgres
 */

        String driver1 = "org.postgresql.Driver";
        String pass1 = "d3sDl4RpHE";//
        String url1 = "jdbc:postgresql://plr6-02.unraveldata.com:7432/hive";
        String user1 = "hive";

        String driver5 = "com.mysql.cj.jdbc.Driver";
        String pass5 = "H3fIelqqHR";//
        String url5 = "jdbc:mysql://ricoh-gboy7eiyf3-sf-db.cdqdxg0obmnc.us-east-1.rds.amazonaws.com:3306/unravel?useSSL=false";
        String user5 = "unravel";


     /*   unravel.database.username=unravel
        unravel.database.password=AES(2312667786ff3b76e0f78a5d61c6653cdbWT7K6kuxOyXkRv+Vn550aH4g3RVy5ql8v+bgHOI8s=)
        unravel.database.jdbc.url=jdbc:mysql://ricoh-gboy7eiyf3-sf-db.cdqdxg0obmnc.us-east-1.rds.amazonaws.com:3306/unravel?useSSL=false
        unravel.database.python3.sqlalchemy.url=mysql+pymysql://{credentials}@ricoh-gboy7eiyf3-sf-db.cdqdxg0obmnc.us-east-1.rds.amazonaws.com:3306/unravel

 ./manager support password decrypt 'AES(2312667786ff3b76e0f78a5d61c6653cdbWT7K6kuxOyXkRv+Vn550aH4g3RVy5ql8v+bgHOI8s=)’

        H3fIelqqHR*/




        HiveMetastoreConnection hiveMetastoreConnection = new HiveMetastoreConnection(url5, driver5, user5, pass5);
        System.out.println(hiveMetastoreConnection.getUrl());
        System.out.println(hiveMetastoreConnection.getSqlConnection());
      //  HiveMetaStore2.getAllTable(hiveMetastoreConnection.getSqlConnection());

       // createTable(hiveMetastoreConnection.getSqlConnection());

        //new Main().getTablesCountQuery("SNOWFLAKE", "ACCOUNT_USAGE");
    }

    public static void createTable(Connection sqlConnection) throws SQLException {
        // create statement
        Statement stmt = sqlConnection.createStatement();
        String query ="CREATE TABLE Per (\n" +
                "    PersonID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Address varchar(255),\n" +
                "    City varchar(255)\n" +
                ")";
        // execute statement
        for (int i = 0; i < 100; i++) {
            stmt.execute(query);
        }
        sqlConnection.close();

    }

    public static void fun() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
            //update/insert table details
        }

    }

    public enum SnowflakeTable {
        QUERY_HISTORY,

        QUERY_HISTORY_BY_USER,

        QUERY_HISTORY_BY_WAREHOUSE,
        WAREHOUSE_METERING_HISTORY,
        WAREHOUSE_LOAD_HISTORY,
        WAREHOUSE_EVENTS_HISTORY,
        WAREHOUSES,
        WAREHOUSES_SNAPSHOT,
        WAREHOUSE_PARAMETERS,
        METERING_HISTORY,
        METERING_DAILY_HISTORY,
        ACCESS_HISTORY,
        BASE_EVENTS,
        EVENTS,
        TABLES,
        LOG,
        QUERY_PROFILE,
        DATABASE_STORAGE_USAGE_HISTORY,
        STAGE_STORAGE_USAGE_HISTORY,
        SEARCH_OPTIMIZATION_HISTORY,
        REPLICATION_GROUP_USAGE_HISTORY,
        DATABASE_REPLICATION_USAGE_HISTORY,
        DATA_TRANSFER_HISTORY,
        AUTOMATIC_CLUSTERING_HISTORY,
        SNOWPIPE_STREAMING_FILE_MIGRATION_HISTORY,
        AUTO_REFRESH_REGISTRATION_HISTORY
    }
    private static final List<SnowflakeTable> tableList = new ArrayList<SnowflakeTable>() {
        {
            add(SnowflakeTable.METERING_DAILY_HISTORY);
            add(SnowflakeTable.ACCESS_HISTORY);
            add(SnowflakeTable.METERING_HISTORY);
            add(SnowflakeTable.QUERY_HISTORY);
            add(SnowflakeTable.WAREHOUSE_EVENTS_HISTORY);
            add(SnowflakeTable.WAREHOUSE_LOAD_HISTORY);
            add(SnowflakeTable.WAREHOUSE_METERING_HISTORY);
            add(SnowflakeTable.TABLES);
            add(SnowflakeTable.WAREHOUSES);
            add(SnowflakeTable.WAREHOUSE_PARAMETERS);
        }
    };

    private String getTablesCountQuery1(String database, String schema) {
        StringBuilder strSelect = new StringBuilder();
        for (SnowflakeTable snowflakeTable : Main.tableList) {
            if (snowflakeTable != SnowflakeTable.WAREHOUSES && snowflakeTable != SnowflakeTable.WAREHOUSE_PARAMETERS) {
                strSelect.append(" SELECT '").append(snowflakeTable.name())
                        .append("' as table_name, 1 as row_count FROM ")
                        .append(database).append('.')
                        .append(schema).append('.')
                        .append(snowflakeTable.name())
                        .append(" limit 1 ")
                        .append(" UNION ALL");
            }
        }
        String queryString = strSelect.toString();
        int lastIndex = queryString.lastIndexOf("UNION ALL");
        if (lastIndex == -1) {
            return queryString;
        }
        System.out.println("query is {} "+ queryString.substring(0, lastIndex));
        return queryString.substring(0, lastIndex);
    }
/*
select 'query_history' as table_name, count(cnt) as row_count from (select 1 as cnt from query_history limit 1)
union all
select 'warehouse_events_history' as table_name, 1 as row_count from (select 1 as cnt from warehouse_events_history limit 1)
union all
select 'warehouse_load_history' as table_name, 1 as row_count from (select 1 as cnt from warehouse_load_history limit 1)
union all
select 'warehouse_metering_history' as table_name, 1 as row_count from (select 1 as cnt from warehouse_metering_history limit 1);
 */

    private String getTablesCountQuery(String database, String schema) {
        StringBuilder strSelect = new StringBuilder();
        for (SnowflakeTable snowflakeTable : Main.tableList) {
            if (snowflakeTable != SnowflakeTable.WAREHOUSES && snowflakeTable != SnowflakeTable.WAREHOUSE_PARAMETERS) {
                strSelect.append(" SELECT '").append(snowflakeTable.name())
                        .append("' as table_name, 1 as row_count FROM ")
                        .append("(select 1 as cnt from ")
                        .append(database).append('.')
                        .append(schema).append('.')
                        .append(snowflakeTable.name())
                        .append(" limit 1 )")
                        .append(" UNION ALL");
            }
        }
        String queryString = strSelect.toString();
        int lastIndex = queryString.lastIndexOf("UNION ALL");
        if (lastIndex == -1) {
            return queryString;
        }
        System.out.println( queryString.substring(0, lastIndex));
        return queryString.substring(0, lastIndex);
    }

}
