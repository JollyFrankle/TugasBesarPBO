/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.JobHistory;
import model.Transaksi;
/**
 *
 * I Putu Agestya Pramana
 * 200710994
 */
public class TabelJobHistory extends AbstractTableModel {
    List<JobHistory> list;
    
    public TabelJobHistory(List<JobHistory> list){
        this.list = list;
    }
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public int getColumnCount(){
        return 3;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getPegawai().getNama();
            case 1:
                return list.get(rowIndex).getTglLog().format(Transaksi.LOCAL_DTF);
            case 2:
                return list.get(rowIndex).getAktivitas();
            case 99:
                return list.get(rowIndex);
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "Nama Pegawai";
            case 1:
                return "Tanggal Log";
            case 2:
                return "Aktivitas";
            default:
                return null;
        }
    }
}
