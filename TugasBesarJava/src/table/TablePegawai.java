/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Pegawai;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author M S I
 */
public class TablePegawai extends AbstractTableModel{
    List<Pegawai> list;
    
    public TablePegawai(List<Pegawai> list){
        this.list = list;
    }
    
    public int getRowCount(){
        return list.size();
    }
    
    public int getColumnCount(){
        return 5;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return LocalDate.parse(list.get(rowIndex).getTglLahir(), DateTimeFormatter.ISO_LOCAL_DATE).format(DateTimeFormatter.ofPattern("d MMMM yyyy", new java.util.Locale("id")));
            case 3:
                return list.get(rowIndex).getNoHP();
            case 4:
                return list.get(rowIndex).getJobDesc();
            case 99:
                return list.get(rowIndex);
            default:
                return null;
        }
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "Nama";
            case 2:
                return "Tanggal Lahir";
            case 3:
                return "No HP";
            case 4:
                return "Job Description";
            default:
                return null;
        }
    }
}