package test.conn;
/**
 * @author Tasos Arvanitis
 */
public enum DatabaseType {

    UNKNOWN(-1, "UNKNOWN"),
    OTHER(1, "OTHER"),
    POSTGRESQL(2, "POSTGRESQL"),
    MYSQL(3, "MYSQL"),
    MARIADB(4, "MARIADB"),
    ORACLE(5, "ORACLE"),
    SQLSERVER(6, "SQLSERVER");

    private final int value;
    private final String desc;

    DatabaseType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static String findDesc(int value) {
        String result = DatabaseType.UNKNOWN.getDesc();

        for (DatabaseType databaseType : DatabaseType.values()) {
            if (databaseType.getValue() == value) {
                return databaseType.getDesc();
            }
        }
        return result;
    }

    public static int findValue(String desc) {
        int result = DatabaseType.valueOf("UNKNOWN").getValue();

        try {
            DatabaseType databaseType = EnumUtil.findValue(DatabaseType.class, desc);
            result = databaseType.getValue();
        } catch (Exception ex) {
        }

        return result;
    }


    public static class EnumUtil {
        public static <E extends Enum<E>> E findValue(Class<E> e, String desc) {
            E result = null;
            try {
                result = Enum.valueOf(e, desc);
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
            return result;
        }
    }

}
