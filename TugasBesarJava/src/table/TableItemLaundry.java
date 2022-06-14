/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package table;

import java.util.List;
import model.ItemLaundry;
import javax.swing.table.AbstractTableModel;

/**
 * Nama : Calvin Andrean Suhedy
 * NPM : 200710824
 * Kelas : PBO B
 */
public class TableItemLaundry extends AbstractTableModel {
    List<ItemLaundry> list;
    
    public TableItemLaundry(List<ItemLaundry> list){
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
                return list.get(rowIndex).getIndex()+1;
            case 1:
                return list.get(rowIndex).getJenis();
            case 2:
                return list.get(rowIndex).getBerat();
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
                return "No.";
            case 1:
                return "Jenis";
            case 2:
                return "Berat";
            default:
                return null;           
        }
    }
}