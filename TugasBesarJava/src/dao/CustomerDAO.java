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
public class CustomerDAO {
    private static final DbConnection DBC = new DbConnection();
    private DbConnection dbCon = new DbConnection();
    private Connection con;
    private String sql = "";
    private int test;
    
    public void insertCustomer(Customer c){
        con = dbCon.makeConnection();
        
        String sql = "INSERT INTO customer(id, nama, alamat, noHP) "
                + "VALUES ('" + c.getId() + "', '" + c.getNama() + "', '" + c.getAlamat() + "', '" + c.getNoHP() + "')";
        System.out.println("Adding Customer...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added "+ result + " Customer");
            statement.close();
        }catch(Exception e){
            System.out.println("Error adding Customer...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    public List<Customer> showCustomer(){
        con = dbCon.makeConnection();
        
        
        String sql = "SELECT * FROM customer";
        System.out.println("Mengambil data Customer..");
        
        List<Customer> list1 = new ArrayList();
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null){
                while(rs.next()){
                    Customer c = new Customer(rs.getString("nama"), rs.getString("alamat"), rs.getString("noHP"));
                    list1.add(c);
                }
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("Error reading database...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        
        return list1;
    }
    
    public void updateCustomer(Customer c, int id){
        con = dbCon.makeConnection();
        String sql = "UPDATE pegawai SET nama = '" + c.getNama() + "', "
                + "alamat = '" + c.getAlamat() + "', "
                + "noHP = '" + c.getNoHP() + "' "
                + "WHERE id = '" + id + ";";
        System.out.println("Editing Customer....");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited "+ result + " Customer " + id);
            statement.close();
        }catch(Exception e){
            System.out.println("Error editing Customer...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    public void deleteCustomer(int id){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM customer WHERE id = '" + id + "'";
        System.out.println("Deleting customer....");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Deleted "+ result + " customer " + id);
            statement.close();
        }catch(Exception e){
            System.out.println("Error deleting customer...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }    
    
    public Customer searchCustomer(int id){
        con = dbCon.makeConnection();
        
        String sql = "SELECT * FROM customer  WHERE id = '" + id + "'";
        System.out.println("Mencari customer...");
        
        Customer c = null;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        
            if(rs != null){
                  while(rs.next()){
                    c = new Customer(rs.getString("nama"), rs.getString("alamat"), rs.getString("noHP"));
                }
            }
            rs.close();
            statement.close();
        } catch(Exception e){
                System.out.println("Error reading Database...");
                System.out.println(e);
        }
        dbCon.closeConnection();

        return c;
    }
}
