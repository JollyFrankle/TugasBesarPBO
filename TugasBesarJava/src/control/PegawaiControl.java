package control;

import java.util.List;
import dao.PegawaiPreparedDAO;
import model.Pegawai;
import table.TablePegawai;
/**
 *
 * @author Tanto
 */
public class PegawaiControl {
    private PegawaiPreparedDAO dBao = new PegawaiPreparedDAO();
    
    public void InsertDataPegawaI(Pegawai p){
        dBao.insertPegawai(p);
    }
    
    public Pegawai searchPegawai(String id){
        Pegawai p = null;
        p = (Pegawai) dBao.searchPegawai(id); //ragu
        
        return p;
    }
    
    public void updateDataPegawai(Pegawai p){
        dBao.updatePegawai(p);
    }
    
    public void deleteDataPegawai(String id){
        dBao.deletePegawai(id);
    }
    
    public List<Pegawai> showListAllPegawai(){
       List<Pegawai> data = dBao.searchPegawai();
       
       return data;
    }    
    
}
