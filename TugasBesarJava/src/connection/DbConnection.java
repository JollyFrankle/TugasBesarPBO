/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Jolly Hans Frankle - 200710932
package connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection CON;
    public static final String URL = "jdbc:mysql://";
    public static final String PATH = "109.106.254.101:3306/u764338354_tubes?useSSL=false";
    public static final String USER = "u764338354_tubes";
    public static final String PWD = "tubesPBOB1";
    
    // Additional: colour indicators for SUCCESS, DANGER, or WARNING
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    
    public Connection makeConnection() {        
        try {
            CON = DriverManager.getConnection(URL + PATH, USER, PWD);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "[E] [DBcon/make]: " + e.toString());
        }
        
        return CON;
    }
    
    public void closeConnection() {
        try {
            CON.close();
        } catch (Exception e) {
            System.out.println(ANSI_RED + "[E] [DBcon/close]: " + e.toString());
        }
    }
}
