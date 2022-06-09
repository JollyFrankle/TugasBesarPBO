/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Customer;

/**
 * Jolly Hans Frankle
 * 200710932
 * Praktikum PBO kelas B
 */
public class TableTesting extends AbstractTableModel {
    private List<Customer> list;
    
    public TableTesting(List<Customer> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer activeRow = list.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return activeRow.getId();
            case 1:
                return activeRow.getNama();
            case 2:
                return activeRow.getAlamat();
            case 3:
                return activeRow.getNoHP();
            case 99:
                return activeRow;
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "ID";
            case 1:
                return "Nama";
            case 2:
                return "Alamat";
            case 3:
                return "No. HP";
            default:
                return null;
        }
    }
}
