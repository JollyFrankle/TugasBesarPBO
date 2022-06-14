package control;

/**
 *
 * @author Tanto
 */

import dao.TransaksiPreparedDAO;
import java.util.*;
import model.Pegawai;
import model.Transaksi;
import table.TableTransaksi;

public class TransaksiControl {
    private TransaksiPreparedDAO tpDAO = new TransaksiPreparedDAO();
    
    public void insertDataTransaksi(Transaksi t, Pegawai p){
        // Insert dibutuhkan juga siapa pegawai yang menerima dan mencatat ini ke sistem.
        tpDAO.insertTransaksi(t, p);
    }
    
    public void updateDataTransaksi(Transaksi t){
        tpDAO.updateTransaksi(t);
    }
    
    public void deleteDataTransaksi(int id){
        tpDAO.deleteTransaksi(id);
    }
    
    public TableTransaksi searchTransaksi(String input){
        return new TableTransaksi(tpDAO.searchTransaksi(input));
    }
    
    public List<Transaksi> showDataTransaksi(){
        List<Transaksi> data = tpDAO.searchTransaksi();
        
        return data;
    }
}
