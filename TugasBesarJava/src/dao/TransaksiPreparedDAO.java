package dao;

import connection.DbConnection;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.JobHistory;
import model.Pegawai;
import model.Transaksi;

/**
 *
 * @author M S I
 * @author ASUS
 */
public class TransaksiPreparedDAO {
    private static final DbConnection DBC = new DbConnection();
    private Connection con;
    
    public int insertTransaksi(Transaksi T, Pegawai P){
        con = DBC.makeConnection();
        int rowCount = 0;
        
        String sql = "INSERT INTO transaksi (idCustomer, tglMasuk, tglSelesai, tglAmbil, tipeLayanan, beratPakaian, beratSelimut, beratBoneka) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, T.getCustomer().getId());
            st.setString(2, T.getTglMasuk().format(Transaksi.DEFAULT_DTF));
            st.setString(3, T.getTglSelesai().format(Transaksi.DEFAULT_DTF));
            st.setString(4, T.getTglAmbil().format(Transaksi.DEFAULT_DTF));
            st.setString(5, T.getTipeLayanan().toString());
            st.setFloat(6, T.getBeratPakaian());
            st.setFloat(7, T.getBeratSelimut());
            st.setFloat(8, T.getBeratBoneka());
            
            rowCount = st.executeUpdate();
            
            int lastInsertId = 0;
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                // https://stackoverflow.com/questions/5513180/java-preparedstatement-retrieving-last-inserted-id
                lastInsertId = rs.getInt(1);
            }

            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/insertTransaksi] Added " + rowCount + " row(s), with the last inserted id of " + lastInsertId + ".");
            st.close();
            
            // Kemudian tambahkan juga job history pertamanya
            JobHistoryPreparedDAO jhDAO = new JobHistoryPreparedDAO();
            JobHistory JH = new JobHistory(
                    0,
                    lastInsertId,
                    P,
                    LocalDateTime.now().format(Transaksi.DEFAULT_DTF),
                    "Menerima dan mencatat transaksi dari customer ke dalam sistem."
            );
            jhDAO.insertJobHistory(JH);
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [TransaksiPreparedDAO/insertTransaksi] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
    
    public List<Transaksi> searchTransaksi(String input){
        con = DBC.makeConnection();
        
        String sql = "SELECT t.*, c.*, "
                + "(SELECT j.tglLog FROM job_history j WHERE j.idTransaksi = t.id ORDER BY tglLog DESC LIMIT 1) AS jobTanggal, "
                + "(SELECT j.aktivitas FROM job_history j WHERE j.idTransaksi = t.id ORDER BY tglLog DESC LIMIT 1) AS jobAktivitas "
                + "FROM transaksi as t JOIN customer as c ON c.id = t.id "
                + "WHERE c.nama LIKE ? "
                + "OR t.tglMasuk LIKE ? "
                + "OR t.tglSelesai LIKE ? "
                + "OR t.tglAmbil LIKE ? "
                + "OR t.tipeLayanan LIKE ? "
                + "OR (t.beratPakaian + t.beratSelimut + t.beratBoneka) LIKE ? "; // total berat saja yang ditampilkan di tabel
        
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
                    Customer C = new Customer(
                        rs.getInt("c.id"),
                        rs.getString("c.nama"), 
                        rs.getString("c.alamat"), 
                        rs.getString("c.noHP")
                    );
                    
                    String lastActivity = LocalDateTime.parse(rs.getString("jobTanggal"), Transaksi.DEFAULT_DTF).format(Transaksi.LOCAL_DTF) + " - " + rs.getString("jobAktivitas");
                    Transaksi t = new Transaksi(
                        rs.getInt("t.id"),
                        lastActivity,
                        rs.getString("t.tglMasuk"), 
                        rs.getString("t.tglSelesai"), 
                        rs.getString("t.tglAmbil"), 
                        rs.getString("t.tipeLayanan"),
                        rs.getFloat("t.beratPakaian"),
                        rs.getFloat("t.beratSelimut"),
                        rs.getFloat("t.beratBoneka"),
                        C
                    );
                    list.add(t);
                    rowCount++;
                }
            }
            
            
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/searchTransaksi] Fetched " + rowCount + " row(s).");
            rs.close();
            st.close();
        } catch(SQLException e){
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
                + "tglMasuk = ?, "
                + "tglSelesai = ?, "
                + "tglAmbil = ?,"
                + "tipeLayanan = ?, "
                + "beratPakaian = ?, "
                + "beratSelimut = ?, "
                + "beratBoneka = ? "
                + "WHERE id = ?;";
        
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, T.getCustomer().getId());
            st.setString(2, T.getTglMasuk().format(Transaksi.DEFAULT_DTF));
            st.setString(3, T.getTglSelesai().format(Transaksi.DEFAULT_DTF));
            st.setString(4, T.getTglAmbil() != null ? T.getTglAmbil().format(Transaksi.DEFAULT_DTF) : null);
            st.setString(5, T.getTipeLayanan().toString());
            st.setFloat(6, T.getBeratPakaian());
            st.setFloat(7, T.getBeratSelimut());
            st.setFloat(8, T.getBeratBoneka());
            st.setInt(9, T.getId());
            
            rowCount = st.executeUpdate();
            System.out.println(DbConnection.ANSI_GREEN + "[OK] [TransaksiPreparedDAO/updateTransaksi] Updated " + rowCount + " row(s).");
            st.close();
        }catch(SQLException e){
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
        }catch(SQLException e){
            System.out.println(DbConnection.ANSI_RED + "[E] [TransaksiPreparedDAO/deleteTransaksi] Error: " + e.toString());
        }
        DBC.closeConnection();
        return rowCount;
    }
}