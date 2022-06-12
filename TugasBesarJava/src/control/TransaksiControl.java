package control;

/**
 *
 * @author Tanto
 */

import dao.TransaksiPreparedDAO;
import java.util.*;
import model.Transaksi;
import table.TableTransaksi;

public class TransaksiControl {
    private TransaksiPreparedDAO tpDAO = new TransaksiPreparedDAO();
    
    public void insertDataTransaksi(Transaksi t){
        tpDAO.insertTransaksi(t);
    }
    
    public void updateTransaksi(Transaksi t){
        tpDAO.updateTransaksi(t);
    }
    
    public void deleteTransaksi(int id){
        tpDAO.deleteTransaksi(id);
    }
    
    public Transaksi searchTransaksi(String input){
        Transaksi t = (Transaksi) tpDAO.searchTransaksi(input);
        
        return t;
    }
    
    public List<Transaksi> showDataTransaksi(){
        List<Transaksi> data = tpDAO.searchTransaksi();
        
        return data;
    }
}
