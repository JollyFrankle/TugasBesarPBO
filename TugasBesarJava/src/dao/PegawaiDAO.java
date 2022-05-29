package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pegawai;
/**
 *
 * @author M S I
 * @author ASUS
 */
public class PegawaiDAO {
    private static final DbConnection DBC = new DbConnection();
    private DbConnection dbCon = new DbConnection();
    private Connection con;
    private String sql = "";
    private int test;

    public PegawaiDAO(int test) {
        this.test = test;
    }
    
    public void insertPegawai(Pegawai p){
        con = dbCon.makeConnection();
        
        String sql = "INSERT INTO pegawai(id, nama, tglLahir, noHP, jobDesc) "
                + "VALUES ('" + p.getId() + "', '" + p.getNama() + "', '" + p.getTglLahir() + "', '" + p.getNoHP() + "', '" + p.getJobDesc() + "')";
        System.out.println("Adding Pegawai...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added "+ result + " Pegawai");
            statement.close();
        }catch(Exception e){
            System.out.println("Error adding Pegawai...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    public int getTest(){
        return test;
    }
    
    public List<Pegawai> showPegawai(){
        con = dbCon.makeConnection();
        
        
        String sql = "SELECT * FROM pegawai";
        System.out.println("Mengambil data Pegawai..");
        
        List<Pegawai> list1 = new ArrayList();
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null){
                while(rs.next()){
                    Pegawai p = new Pegawai(rs.getString("id"), rs.getString("nama"), rs.getString("tglLahir"), rs.getString("noHP"), rs.getString("jobDesc"));
                    list1.add(p);
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
    
    public void updatePegawai(Pegawai p, String id){
        con = dbCon.makeConnection();
        String sql = "UPDATE pegawai SET nama = '" + p.getNama() + "', "
                + "tglLahir = '" + p.getTglLahir() + "', "
                + "noHP = '" + p.getNoHP() + "' "
                + "WHERE id = '" + id + ";";
        System.out.println("Editing Pegawai....");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited "+ result + " Pegawai " + id);
            statement.close();
        }catch(Exception e){
            System.out.println("Error editing Pegawai...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    public void deletePegawai(String id){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM pegawai WHERE id = '" + id + "'";
        System.out.println("Deleting pegawai....");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Deleted "+ result + " Pegawai " + id);
            statement.close();
        }catch(Exception e){
            System.out.println("Error deleting Pegawai...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }    
}
