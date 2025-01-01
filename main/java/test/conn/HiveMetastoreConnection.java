package test.conn;

import org.apache.commons.codec.digest.DigestUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HiveMetastoreConnection {

    private DatabaseType hmsDbType = DatabaseType.MYSQL;
    private final String metastoreId;
    private final String url;
    private final String driver;
    private final String username;
    private final String password;
    private Connection sqlConnection;

    // All input parameters are non-null
    public HiveMetastoreConnection(String url, String driver, String username, String password) {
        this.metastoreId = DigestUtils.md5Hex(url);
        this.url = url;
        this.driver = driver;
        this.username = username;
        this.password = password;
    }

    public String getMetastoreId() {
        return metastoreId;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getSqlConnection() {
        if (sqlConnection == null) {
            sqlConnection = openConnection();
        }
        return sqlConnection;
    }

    public void closeSqlConnection() {
        if(sqlConnection != null) {
            try {
                sqlConnection.close();
            } catch (Exception e) {
                throw new RuntimeException("unexpected exception: during init, e=" + e);
            } finally {
                sqlConnection = null;
            }
        }
    }

    /**
     * Open a connection
     * @return
     */
    private Connection openConnection() {

        Connection sqlConnection = null;
        try {//oracle.jdbc.driver.OracleDrive
            Class.forName(driver);
            sqlConnection = DriverManager.getConnection(url, username, password);

            // Extract database type from connectionUrl
            String[] urlParts = url.split(":");
            if (urlParts.length >= 2) {
                String db = urlParts[1];
                if (db.equalsIgnoreCase("postgresql")) {
                    hmsDbType = DatabaseType.POSTGRESQL;
                } else if (db.equalsIgnoreCase("mysql")) {
                    hmsDbType = DatabaseType.MYSQL;
                } else if (db.equalsIgnoreCase("mariadb")) {
                    hmsDbType = DatabaseType.MARIADB;
                } else if (db.equalsIgnoreCase("oracle")) {
                    hmsDbType = DatabaseType.ORACLE;
                } else if (db.equalsIgnoreCase("sqlserver")) {
                    hmsDbType = DatabaseType.SQLSERVER;
                } else {
                    hmsDbType = DatabaseType.OTHER;
                }
            }
        }catch (RuntimeException | SQLException | ClassNotFoundException e)
        {
            System.out.println("exception "+e);
        }

        return sqlConnection;
    }

    public DatabaseType getHmsDbType() {
        return hmsDbType;
    }
}
