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
    private static final int BASE = 1;
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
        return BASE + 4;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer activeRow = list.get(rowIndex);
        switch(columnIndex) {
            case 0:
                // Nomor 0 sudah pasti visually hidden:
                return activeRow;
            case 1:
                return activeRow.getId();
            case 2:
                return activeRow.getNama();
            case 3:
                return activeRow.getAlamat();
            case 4:
                return activeRow.getNoHP();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 1:
                return "ID";
            case 2:
                return "Nama";
            case 3:
                return "Alamat";
            case 4:
                return "No. HP";
            default:
                return null;
        }
    }
}
