/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package connection;

import dao.CustomerPreparedDAO;
import java.util.Random;
import model.Customer;

// selamat pagi
/**
 * Jolly Hans Frankle
 * 200710932
 * Praktikum PBO kelas B
 */
public class DAOTestKit {
    public static void main(String[] args) {
        // RUN THIS FILE: SHIFT+F6
        
//        DbConnection db = new DbConnection();
//        for(int i=0; i<100; i++) {
//            db.makeConnection();
//            System.out.println(DbConnection.ANSI_GREEN + "[OK] " + (i+1));
//            db.closeConnection();
//        }
        
        CustomerPreparedDAO cDAO = new CustomerPreparedDAO();
        Customer C = new Customer(
                8,
                getRandomStr(15),
                getRandomStr(10),
                getRandomStr(12)
        );
        
        // Test here:
        cDAO.insertCustomer(C);
        cDAO.searchCustomer("");
        cDAO.updateCustomer(C);
        cDAO.deleteCustomer(7);
    }
    
    public static String getRandomStr(int targetLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetLength);
        for (int i = 0; i < targetLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
