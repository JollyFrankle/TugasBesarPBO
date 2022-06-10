package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pegawai;

/**
 *
 * @author M S I
 * @author ASUS
 */
public class PegawaiPreparedDAO {
    private static final DbConnection DBC = new DbConnection();
    private Connection con;
    
    public int insertPegawai(Pegawai P){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "INSERT INTO pegawai (id, nama, tglLahir, noHP, jobDesc) VALUES (?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, P.getId());
            st.setString(2, P.getNama());
            st.setString(3, P.getTglLahir());
            st.setString(4, P.getNoHP());
            st.setString(5, P.getJobDesc());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [PegawaiPreparedDAO/insertPegawai] Added " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [PegawaiPreparedDAO/insertPegawai] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public List<Pegawai> searchPegawai(String input){
        con = DBC.makeConnection();
        
        String sql = "SELECT * FROM pegawai "
                + "WHERE nama LIKE ? "
                + "OR noHP LIKE ? "
                + "OR jobDesc LIKE ?;";
        List<Pegawai> list = new ArrayList();
        
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
                    Pegawai p = new Pegawai(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("tglLahir"),
                        rs.getString("noHP"),
                        rs.getString("jobDesc")
                    );
                    list.add(p);
                    rowCount++;
                }
            }
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [PegawaiPreparedDAO/searchPegawai] Fetched " + rowCount + " row(s).");
            rs.close();
            st.close();
        } catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [PegawaiPreparedDAO/searchPegawai] Error: " + e.toString());
        }
        DBC.closeConnection();
        
        return list;
    }
    
    public List<Pegawai> searchPegawai() {
        return searchPegawai("");
    }
    
    public int updatePegawai(Pegawai P){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "UPDATE pegawai SET nama = ?, "
                + "tglLahir = ?, "
                + "noHP = ?,"
                + "jobDesc = ? "
                + "WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, P.getNama());
            st.setString(2, P.getTglLahir());
            st.setString(3, P.getNoHP());
            st.setString(4, P.getJobDesc());
            st.setString(5, P.getId());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [PegawaiPreparedDAO/updatePegawai] Updated " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [PegawaiPreparedDAO/updatePegawai] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public int deletePegawai(String id){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "DELETE FROM pegawai WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            // USAGE: set[Int/String/...](nomor parameter (urutan tanda `?`) , <konten>);
            st.setString(1, id);
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [PegawaiPreparedDAO/deletePegawai] Deleted " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [PegawaiPreparedDAO/deletePegawai] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
}
