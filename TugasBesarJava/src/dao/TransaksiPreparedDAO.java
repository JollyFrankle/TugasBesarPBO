package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Transaksi;

/**
 *
 * @author M S I
 * @author ASUS
 */
public class TransaksiPreparedDAO {
    private static final DbConnection DBC = new DbConnection();
    private Connection con;
    
    public int insertTransaksi(Transaksi T){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "INSERT INTO transaksi (idCustomer, status, tglMasuk, tglSelesai, tglAmbil, tipeLayanan, itemLaundry) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, T.getCustomer().getId());
            st.setString(2, T.getStatus());
            st.setString(3, T.getTglMasuk());
            st.setString(4, T.getTglSelesai());
            st.setString(5, T.getTglAmbil());
            st.setString(6, T.getTipeLayanan().toString());
            st.setString(7, T.getListItemJSON());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/insertTransaksi] Added " + rowCount + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [TransaksiPreparedDAO/insertTransaksi] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public List<Transaksi> searchTransaksi(String input){
        con = DBC.makeConnection();
        
        
        String sql = "SELECT t.*, c.* FROM transaksi as t JOIN customer as c ON c.id = t.id "
                + "WHERE c.nama LIKE ? "
                + "OR t.tglMasuk LIKE ? "
                + "OR t.tglSelesai LIKE ? "
                + "OR t.tglAmbil LIKE ? "
                + "OR t.tipeLayanan LIKE ? "
                + "OR t.status LIKE ?;";
        
        List<Transaksi> list = new ArrayList();
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + input + "%");
            st.setString(2, "%" + input + "%");
            st.setString(3, "%" + input + "%");
            st.setString(4, "%" + input + "%");
            st.setString(5, "%" + input + "%");
            st.setString(6, "%" + input + "%");
            
            ResultSet rs = st.executeQuery();
            
            int rowCount = 0;
            
            if(rs != null){
                while(rs.next()){
                    Customer c = new Customer(
                            rs.getInt("c.id"),
                            rs.getString("c.nama"), 
                            rs.getString("c.alamat"), 
                            rs.getString("noHP")
                    );
                    String itemL = rs.getString("t.itemLaundry");
                    Transaksi t = new Transaksi(
                            rs.getInt("t.id"),
                            rs.getString("t.status"), 
                            rs.getString("t.tglMasuk"), 
                            rs.getString("t.tglSelesai"), 
                            rs.getString("t.tglAmbil"), 
                            rs.getString("t.tipeLayanan"),
                            itemL == null ? "[]" : itemL,
                            c
                    );
                    list.add(t);
                    rowCount++;
                }
            }
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/searchTransaksi] Fetched " + rowCount + " row(s).");
            rs.close();
            st.close();
        } catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [TransaksiPreparedDAO/searchTransaksi] Error: " + e.toString());
        }
        DBC.closeConnection();
        
        return list;
    }
    
    public List<Transaksi> searchTransaksi() {
        return searchTransaksi("");
    }
    
    public int updateTransaksi(Transaksi T){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "UPDATE transaksi "
                + "SET idCustomer = ?, "
                + "status = ?, "
                + "tglMasuk = ?, "
                + "tglSelesai = ?, "
                + "tglAmbil = ?, "
                + "tipeLayanan = ? "
                + "WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, T.getCustomer().getId());
            st.setString(2, T.getStatus());
            st.setString(3, T.getTglMasuk());
            st.setString(4, T.getTglSelesai());
            st.setString(5, T.getTglAmbil());
            st.setString(6, T.getTipeLayanan().toString());
            st.setInt(7, T.getIdTransaksi());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/updateTransaksi] Updated " + rowCount + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [TransaksiPreparedDAO/updateTransaksi] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public int deleteTransaksi(int id){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "DELETE FROM transaksi WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            
            rowCount = st.executeUpdate();
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/deleteTransaksi] Deleted " + rowCount + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [TransaksiPreparedDAO/deleteTransaksi] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
}