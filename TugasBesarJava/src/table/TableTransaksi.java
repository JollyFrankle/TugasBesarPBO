/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Transaksi;

/**
 *
 * @author M S I
 */
public class TableTransaksi extends AbstractTableModel{
    List<Transaksi> list;
    
    public TableTransaksi(List<Transaksi> list){
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
                return list.get(rowIndex).getIdTransaksi();
            case 1:
                return list.get(rowIndex).getCustomer().getNama();
            case 2:
                return list.get(rowIndex).getTglMasuk();
            case 3:
                return list.get(rowIndex).getTglSelesai();
            case 4:
                return list.get(rowIndex).getTglAmbil();
            case 5:
                return list.get(rowIndex).getTipeLayanan();
            case 6:
                return list.get(rowIndex).getListItemJSON();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Transaksi";
            case 1:
                return "Nama Customer";
            case 2:
                return "Tanggal Masuk";
            case 3:
                return "Tanggal Selesai";
            case 4:
                return "Tanggal Ambil";
            case 5:
                return "Tipe Layanan";
            case 6:
                return "List Item";
            default:
                return null;
        }
    }
}
