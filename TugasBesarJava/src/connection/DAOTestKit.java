/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package connection;

import dao.CustomerPreparedDAO;
import dao.PegawaiPreparedDAO;
import dao.PegawaiPreparedDAOMultithread;
import dao.TransaksiPreparedDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import model.Customer;
import model.Pegawai;
import model.Transaksi;

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
                1,
                getRandomStr(15),
                getRandomStr(10),
                getRandomStr(12)
        );
        
        PegawaiPreparedDAOMultithread pDAO = new PegawaiPreparedDAOMultithread();
        
        
        // Test here:
//        cDAO.insertCustomer(C);
//        cDAO.searchCustomer();
//        cDAO.updateCustomer(C);
//        cDAO.deleteCustomer(7);
        
        for(int i=0; i<500; i++) {
            new Thread(() -> {
                Pegawai P = new Pegawai(
                    getRandomStr(8),
                    getRandomStr(15),
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    getRandomStr(12),
                    getRandomStr(50)
                );

                new Thread(() -> {
                    pDAO.insertPegawai(P);
                    pDAO.deletePegawai(P.getId());
                }).start();
                new Thread(() -> {
                    pDAO.searchPegawai();
                }).start();
                new Thread(() -> {
                    pDAO.updatePegawai(P);
                }).start();
            }).start();
        }
        
//        TransaksiPreparedDAO tDAO = new TransaksiPreparedDAO();
//        Transaksi T = new Transaksi(
//                4,
//                "BELUM",
//                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
//                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
//                null,
//                "REGULER",
//                C
//        );
//        tDAO.insertTransaksi(T);
//        tDAO.searchTransaksi();
//        tDAO.updateTransaksi(T);
//        tDAO.deleteTransaksi(T.getIdTransaksi());
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
