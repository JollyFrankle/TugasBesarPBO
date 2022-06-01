package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
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
    private DbConnection dbCon = new DbConnection();
    private Connection con;
    
    public int insertCustomer(Customer C) {
        con = dbCon.makeConnection();
        int retVal = 0;
        
        String sql = "INSERT INTO customer(nama, alamat, noHP) VALUES (?, ?, ?);";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, C.getNama());
            st.setString(2, C.getAlamat());
            st.setString(3, C.getNoHP());
            
            int result = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/insertCustomer] Added " + result + " row(s).");
            retVal = result;
            st.close();
        } catch(Exception e) {
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/insertCustomer] Error: " + e.toString());
        }
        dbCon.closeConnection();
        return retVal;
    }
    
    public List<Customer> searchCustomer(String input){
        con = dbCon.makeConnection();
        
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
            int result = 0; // jumlah row yang didapatkan dari query tsb
            
            if(rs != null){
                while(rs.next()){
                    Customer c = new Customer(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("noHP")
                    );
                    list.add(c);
                    result++;
                }
            }
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/searchCustomer] Fetched " + result + " row(s).");
            rs.close();
            st.close();
        } catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/searchCustomer] Error: " + e.toString());
        }
        dbCon.closeConnection();
        
        return list;
    }
    
    public void updateCustomer(Customer C){
        con = dbCon.makeConnection();
        String sql = "UPDATE customer SET nama = ?, alamat = ?, noHP = ? WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, C.getNama());
            st.setString(2, C.getAlamat());
            st.setString(3, C.getNoHP());
            st.setInt(4, C.getId());
            
            int result = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/updateCustomer] Updated " + result + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/updateCustomer] Error: " + e.toString());
        }
        dbCon.closeConnection();
    }
    
    public void deleteCustomer(int id){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM customer WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setInt(1, id);
            
            int result = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [CustomerPreparedDAO/deleteCustomer] Deleted " + result + " row(s).");
            st.close();
        }catch(Exception e){
            System.out.println(DbConnection.ANSI_RED + "[E] [CustomerPreparedDAO/deleteCustomer] Error: " + e.toString());
        }
        dbCon.closeConnection();
    }
}
