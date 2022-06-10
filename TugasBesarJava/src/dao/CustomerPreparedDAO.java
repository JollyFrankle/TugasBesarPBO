package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author M S I
 * @author ASUS
 */
public class CustomerPreparedDAO {
    private static final DbConnection DBC = new DbConnection();
    private static Connection con;
    
    public int insertCustomer(Customer C) {
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "INSERT INTO customer(nama, alamat, noHP) VALUES (?, ?, ?);";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, C.getNama());
            st.setString(2, C.getAlamat());
            st.setString(3, C.getNoHP());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/insertCustomer] Added " + rowCount + " row(s).");
            st.close();
        } catch(Exception e) {
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/insertCustomer] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public List<Customer> searchCustomer(String input){
        con = DBC.makeConnection();
        
        String sql = "SELECT * FROM customer "
                + "WHERE nama LIKE ? "
                + "OR alamat LIKE ? "
                + "OR noHP LIKE ?;";
        List<Customer> list = new ArrayList();
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, "%" + input + "%");
            st.setString(2, "%" + input + "%");
            st.setString(3, "%" + input + "%");
            
            ResultSet rs = st.executeQuery();
            int rowCount = 0; // jumlah row yang didapatkan dari query tsb
            
            if(rs != null){
                while(rs.next()){
                    Customer c = new Customer(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("noHP")
                    );
                    list.add(c);
                    rowCount++;
                }
            }
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/searchCustomer] Fetched " + rowCount + " row(s).");
            rs.close();
            st.close();
        } catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/searchCustomer] Error: " + e.toString());
        }
        DBC.closeConnection();
        
        return list;
    }
    
    public List<Customer> showCustomer() {
        return searchCustomer("");
    }
    
    public int updateCustomer(Customer C){
        con = DBC.makeConnection();
        int rowCount = 0;
        String sql = "UPDATE customer SET nama = ?, alamat = ?, noHP = ? WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, C.getNama());
            st.setString(2, C.getAlamat());
            st.setString(3, C.getNoHP());
            st.setInt(4, C.getId());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/updateCustomer] Updated " + rowCount + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/updateCustomer] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public int deleteCustomer(int id){
        con = DBC.makeConnection();
        int rowCount = 0;
        String sql = "DELETE FROM customer WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setInt(1, id);
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/deleteCustomer] Deleted " + rowCount + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/deleteCustomer] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
}
