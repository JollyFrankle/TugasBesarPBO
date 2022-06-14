package control;

/**
 *
 * @author Tanto
 */

import dao.JobHistoryPreparedDAO;
import java.util.List;
import model.Pegawai;
import model.JobHistory;
import table.TabelJobHistory;

public class JobHistoryControl {
    private JobHistoryPreparedDAO tpDAO = new JobHistoryPreparedDAO();
    
    public void insertDataJobHistory(JobHistory t){
        // Insert dibutuhkan juga siapa pegawai yang menerima dan mencatat ini ke sistem.
        tpDAO.insertJobHistory(t);
    }
    
    public void updateDataJobHistory(JobHistory t){
        tpDAO.updateJobHistory(t);
    }
    
    public void deleteDataJobHistory(int id){
        tpDAO.deleteJobHistory(id);
    }
    
    public TabelJobHistory searchJobHistory(int input){
        return new TabelJobHistory(tpDAO.getJobHistory(input));
    }
}
