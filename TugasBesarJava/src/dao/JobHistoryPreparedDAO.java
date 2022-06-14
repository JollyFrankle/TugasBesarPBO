package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.JobHistory;
import model.Pegawai;
import model.Transaksi;

/**
 *
 * @author M S I
 * @author ASUS
 */
public class JobHistoryPreparedDAO {
    private static final DbConnection DBC = new DbConnection();
    private Connection con;
    
    public int insertJobHistory(JobHistory J){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "INSERT INTO job_history (idTransaksi, idPegawai, tglLog, aktivitas) "
                + "VALUES (?, ?, ?, ?);";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, J.getIdTransaksi());
            st.setString(2, J.getPegawai().getId());
            st.setString(3, J.getTglLog().format(Transaksi.DEFAULT_DTF));
            st.setString(4, J.getAktivitas());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [JobHistoryPreparedDAO/insertJobHistory] Added " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [JobHistoryPreparedDAO/insertJobHistory] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public List<JobHistory> getJobHistory(int idTransaksi){
        con = DBC.makeConnection();
        
        String sql = "SELECT j.*, p.* FROM job_history j INNER JOIN pegawai p ON j.idPegawai = p.id"
                + "WHERE j.id = ?;";
        
        List<JobHistory> list = new ArrayList();
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idTransaksi);
            
            ResultSet rs = st.executeQuery();
            
            int rowCount = 0;
            
            if(rs != null){
                while(rs.next()){
                    Pegawai P = new Pegawai(
                        rs.getString("p.id"),
                        rs.getString("p.nama"),
                        rs.getString("p.tglLahir"),
                        rs.getString("p.noHP"),
                        rs.getString("p.jobDesc")
                    );
                    
                    JobHistory J = new JobHistory(
                        rs.getInt("j.id"),
                        rs.getInt("j.idTransaksi"),
                        P,
                        rs.getString("j.tglLog"),
                        rs.getString("j.aktivitas")
                    );
                    list.add(J);
                    rowCount++;
                }
            }
            
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [JobHistoryPreparedDAO/searchJobHistory] Fetched " + rowCount + " row(s).");
            rs.close();
            st.close();
        } catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [JobHistoryPreparedDAO/searchJobHistory] Error: " + e.toString());
        }
        DBC.closeConnection();
        
        return list;
    }
    
    public int updateJobHistory(JobHistory J){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "UPDATE job_history "
                + "SET idPegawai = ?, "
                + "tglLog = ?, "
                + "aktivitas = ? "
                + "WHERE id = ? ";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, J.getPegawai().getId());
            st.setString(2, J.getTglLog().format(Transaksi.DEFAULT_DTF));
            st.setString(3, J.getAktivitas());
            st.setInt(4, J.getId());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [JobHistoryPreparedDAO/updateJobHistory] Updated " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [JobHistoryPreparedDAO/updateJobHistory] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public int deleteJobHistory(int id){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "DELETE FROM job_history WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            
            rowCount = st.executeUpdate();
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [JobHistoryPreparedDAO/deleteJobHistory] Deleted " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [JobHistoryPreparedDAO/deleteJobHistory] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
}