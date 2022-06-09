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
    
    public TableTransaksi showDataTransaksi(String query){
        List<Transaksi> data = tpDAO.searchTransaksi(query);
        TableTransaksi table = new TableTransaksi(data);
        
        return table;
    }
}
