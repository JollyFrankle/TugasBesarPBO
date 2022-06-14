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
        return 7;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getCustomer().getNama();
            case 1:
                return list.get(rowIndex).getTglMasuk().format(Transaksi.LOCAL_DTF);
            case 2:
                return list.get(rowIndex).getTglSelesai().format(Transaksi.LOCAL_DTF);
            case 3:
                return list.get(rowIndex).getTglAmbil() != null ? list.get(rowIndex).getTglAmbil().format(Transaksi.LOCAL_DTF) : "(belum diambil)";
            case 4:
                return list.get(rowIndex).getTipeLayanan().getString("speed");
            case 5:
                return String.format("%.2f", list.get(rowIndex).getTotalBerat()) + "kg";
            case 6:
                return list.get(rowIndex).getLastActivity();
            case 99:
                return list.get(rowIndex);
            default:
                return null;
        }
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "Nama Customer";
            case 1:
                return "Tanggal Masuk";
            case 2:
                return "Tanggal Selesai";
            case 3:
                return "Tanggal Ambil";
            case 4:
                return "Tipe Layanan";
            case 5:
                return "Total Berat";
            case 6:
                return "Aktivitas Terakhir";
            default:
                return null;
        }
    }
}
