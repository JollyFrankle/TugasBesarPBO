package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Pegawai;
import model.Transaksi;
/**
 *
 * @author M S I
 * @author ASUS
 */
public class TransaksiDAO {
//    private static final DbConnection DBC = new DbConnection();
//    private DbConnection dbCon = new DbConnection();
//    private Connection con;
//    private String sql = "";
//    
//    public void insertTransaksi(Transaksi t){
//        con = dbCon.makeConnection();
//        
//        String sql = "INSERT INTO transaksi(id, status, tglMasuk, tglSelesai, tglAmbil, tipeLayanan) "
//                + "VALUES ('" + t.getIdTransaksi() + "', '" + t.getStatus() + "', '" + t.getTglMasuk() + "', '" + t.getTglSelesai() + "', '" + t.getTglAmbil() + "', '" + t.getTipeLayanan() + "')";
//        System.out.println("Adding Transaksi...");
//        
//        try{
//            Statement statement = con.createStatement();
//            int result = statement.executeUpdate(sql);
//            System.out.println("Added "+ result + " Transaksi");
//            statement.close();
//        }catch(Exception e){
//            System.out.println("Error adding Transaksi...");
//            System.out.println(e);
//        }
//        dbCon.closeConnection();
//    }
//    
//    public List<Transaksi> showTransaksi(String query){
//        con = dbCon.makeConnection();
//        
//        
//        String sql = "SELECT t.*, c.* FROM transaksi as t JOIN customer as c ON c.id = t.id WHERE(c.nama LIKE"
//                + "'%" + query + "%'"
//                + "OR t.tglMasuk LIKE '%" + query + "%'"
//                + "OR t.tglSelesai LIKE '%" + query + "%'"
//                + "OR t.tglAmbil LIKE '%" + query + "%'"
//                + "OR t.tipeLayanan LIKE '%" + query + "%'"
//                + "OR t.status LIKE '%" + query + "%'"
//                + "%')";
//        System.out.println("Mengambil data Transaksi..");
//        
//        List<Transaksi> list1 = new ArrayList();
//        
//        try{
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            
//            if(rs != null){
//                while(rs.next()){
//                    Customer c = new Customer(rs.getInt("c.id"), rs.getString("c.nama"), rs.getString("c.alamat"), rs.getString("noHP"));
//                    Transaksi t = new Transaksi(rs.getInt("t.id"),rs.getString("t.status"), rs.getString("t.tglMasuk"), rs.getString("t.tglSelesai"), rs.getString("t.tglAmbil"), rs.getString("t.tipeLayanan"), rs.getString("t.itemLaundry"), c);
//                    list1.add(t);
//                }
//            }
//            rs.close();
//            statement.close();
//        } catch(Exception e){
//            System.out.println("Error reading database...");
//            System.out.println(e);
//        }
//        dbCon.closeConnection();
//        
//        return list1;
//    }
//    
//    public void updateTransaksi(Transaksi t, int id){
//        con = dbCon.makeConnection();
//        String sql = "UPDATE transaksi SET status = '" + t.getStatus() + "', "
//                + "tglMasuk = '" + t.getTglMasuk() + "', "
//                + "tglSelesai = '" + t.getTglSelesai() + "' "
//                + "tglAmbil = '" + t.getTglAmbil() + "' "
//                + "tipeLayanan = '" + t.getTipeLayanan() + "' "
//                + "WHERE id = '" + id + ";";
//        System.out.println("Editing Transaksi....");
//        
//        try{
//            Statement statement = con.createStatement();
//            int result = statement.executeUpdate(sql);
//            System.out.println("Edited "+ result + " Transaksi " + id);
//            statement.close();
//        }catch(Exception e){
//            System.out.println("Error editing Transaksi...");
//            System.out.println(e);
//        }
//        dbCon.closeConnection();
//    }
//    
//    public void deleteTransaksi(int id){
//        con = dbCon.makeConnection();
//        String sql = "DELETE FROM transaksi WHERE id = '" + id + "'";
//        System.out.println("Deleting transaksi....");
//        
//        try{
//            Statement statement = con.createStatement();
//            int result = statement.executeUpdate(sql);
//            System.out.println("Deleted "+ result + " Transaksi " + id);
//            statement.close();
//        }catch(Exception e){
//            System.out.println("Error deleting Transaksi...");
//            System.out.println(e);
//        }
//        dbCon.closeConnection();
//    }
//    
//    public Transaksi searchTransaksi(int id){
//        con = dbCon.makeConnection();
//        
//        String sql = "SELECT * FROM transaksi  WHERE id = '" + id + "'";
//        System.out.println("Mencari transaksi...");
//        
//        Transaksi t = null;
//        try{
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//        
//            if(rs != null){
//                  while(rs.next()){
//                    Customer c = new Customer(rs.getInt("c.id"), rs.getString("c.nama"), rs.getString("c.alamat"), rs.getString("noHP"));
//                    t = new Transaksi(rs.getInt("t.id"),rs.getString("t.status"), rs.getString("t.tglMasuk"), rs.getString("t.tglSelesai"), rs.getString("t.tglAmbil"), rs.getString("t.tipeLayanan"), rs.getString("t.itemLaundry"), c);
//                }
//            }
//            rs.close();
//            statement.close();
//        } catch(Exception e){
//                System.out.println("Error reading Database...");
//                System.out.println(e);
//        }
//        dbCon.closeConnection();
//
//        return t;
//    }
}