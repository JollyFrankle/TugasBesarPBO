/*
 *
 */

package connection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection CON;
    private static DbConnection internalDB;
    public static final String URL = "jdbc:mysql://";
    public static final String PATH = "109.106.254.101:3306/u764338354_tubes?useSSL=false";
    public static final String USER = "u764338354_tubes";
    public static final String PWD = "tubesPBOB1";
    
    // Additional: colour indicators for SUCCESS, DANGER, or WARNING
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    private DbConnection() throws SQLException {
        this.CON = DriverManager.getConnection(URL + PATH, USER, PWD);
    }
    
    public static Connection getDBCon() {
        try {
            if(DbConnection.internalDB == null) {
                DbConnection.internalDB = new DbConnection();
            }
            try {
                Statement st = DbConnection.internalDB.CON.createStatement();
                st.execute("SELECT 1;");
            } catch (SQLException e) {
                DbConnection.internalDB.CON = DriverManager.getConnection(URL + PATH, USER, PWD);
                System.out.println(ANSI_YELLOW + "[OK] [DBcon/make] Database regeneration attempted after timeout.");
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "[E] [DBcon/make]: " + e.getMessage());
        }
        
        return DbConnection.internalDB.CON;
    }
}
