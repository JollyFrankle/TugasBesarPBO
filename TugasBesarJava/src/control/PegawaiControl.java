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
    
    public void InsertDataPegawai(Pegawai p){
        dBao.insertPegawai(p);
    }
    
    public String showDataPegawai(){
        List<Pegawai> data = dBao.searchPegawai();
        
        String pegawaiString = "";
        for(int i=0; i<data.size(); i++){
            pegawaiString += data.get(i).showDataPegawai();
        }
        
        return pegawaiString;
    }
    
    public TablePegawai searchPegawai(String input){
        return new TablePegawai(dBao.searchPegawai(input));
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
    
    public TablePegawai getTablePegawai(String query){
        return new TablePegawai(dBao.searchPegawai(query));
    }
    
}
