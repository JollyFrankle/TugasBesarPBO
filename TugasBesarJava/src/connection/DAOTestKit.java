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
        
        List<Transaksi> listT;
        
        JSONObject obj2 = new JSONObject();
        
        // Layanan:
        String layanan[] = {"CUCI", "SETRIKA"};
        JSONArray arr = new JSONArray().putAll(layanan);
        
        // Speed dan fasilitass
        obj2.put("speed", "EXPRESS");
        obj2.put("facility", arr);
        
        String jsonString = "[{\"id\":12, \"name\":\"Jolly\"}]";
        JSONArray jArr = new JSONArray(jsonString);
        for(int i=0; i<jArr.length(); i++) {
            JSONObject jObj = jArr.getJSONObject(i);
            System.out.println(jObj.get("id"));
        }
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
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "2022-01-12 09:12:22",
                null,
                "{\"facility\":[\"CUCI\",\"SETRIKA\"],\"speed\":\"EXPRESS\"}",
                "[{\"jenis\":\"Pakaian\",\"qty\":8,\"berat\":2.7},{\"jenis\":\"Boneka\",\"qty\":2,\"berat\":0.56}]",
                C
        );
        System.out.println(T.getListItemJSON());
//        
        tDAO.insertTransaksi(T);
        listT = tDAO.searchTransaksi();
        for(Transaksi TR : listT) {
            System.out.println(TR.getListItemJSON());
        }
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
