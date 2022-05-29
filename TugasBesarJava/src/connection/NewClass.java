/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package connection;

/**
 * Jolly Hans Frankle
 * 200710932
 * Praktikum PBO kelas B
 */
public class NewClass {
    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        for(int i=0; i<100; i++) {
            db.makeConnection();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] " + (i+1));
            db.closeConnection();
        }
    }
}
