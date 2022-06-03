/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package connection;

import java.util.List;
import java.util.ArrayList;
import dao.TransaksiPreparedDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import model.Customer;
import model.Pegawai;
import model.Transaksi;
import org.json.JSONArray;
import org.json.JSONObject;

// selamat pagi
/**
 * Jolly Hans Frankle
 * 200710932
 * Praktikum PBO kelas B
 */
public class DAOTestKit {
    private static final TransaksiPreparedDAO tDAO = new TransaksiPreparedDAO();
    
    public static void main(String[] args) {
        // RUN THIS FILE: SHIFT+F6
        
        List<Transaksi> listT = tDAO.searchTransaksi("");
        
        JSONObject obj2 = new JSONObject();
        
        // Layanan:
        String layanan[] = {"CUCI", "SETRIKA"};
        JSONArray arr = new JSONArray().putAll(layanan);
        System.out.println(JSONArray.class.toString());
        
        // Speed dan fasilitass
        obj2.put("speed", "EXPRESS");
        obj2.put("facility", arr);
        
        
        System.out.println(obj2.toString());
        Customer C = new Customer(
                1,
                "nama2",
                "alamat",
                "0812"
        );
        
        Transaksi T = new Transaksi(
                0,
                "BELUM_AKTIF",
                "2022-01-02 00:19:02",
                "2022-01-12 09:12:22",
                null,
                obj2,
                C
        );
        
        tDAO.insertTransaksi(T);
        tDAO.searchTransaksi();
    }
    
    public static String getRandomStr(int targetLength) {
        int leftLimit = 0; // letter 'a'
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
