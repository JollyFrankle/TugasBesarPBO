package model;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Transaksi {
    private int idTransaksi;
    private String status;
    private String tglMasuk;
    private String tglSelesai;
    private String tglAmbil;
    private JSONObject tipeLayanan;
    private List<ItemLaundry> listItem;
    private List<JobHistory> listHistory;
    private Customer customer;

    public Transaksi(int idTransaksi, String status, String tglMasuk, String tglSelesai, String tglAmbil, JSONObject tipeLayanan, List<ItemLaundry> itemLaundry, Customer customer) {
        this.idTransaksi = idTransaksi;
        this.status = status;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.tipeLayanan = tipeLayanan;
        this.listItem = itemLaundry;
        this.customer = customer;
    }
    
    public Transaksi(int idTransaksi, String status, String tglMasuk, String tglSelesai, String tglAmbil, String tipeLayanan, String itemLaundry, Customer customer) {
        this.idTransaksi = idTransaksi;
        this.status = status;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.tipeLayanan = new JSONObject(tipeLayanan);
        this.listItem = new ArrayList();
        this.customer = customer;
        
        // Create JSON array from input:
        JSONArray jArr = new JSONArray(itemLaundry);
        
        // iterate over jArr, masukkan ke list item laundry
        for(int i=0; i<jArr.length(); i++) {
            JSONObject jObj = jArr.getJSONObject(i);
            ItemLaundry IL = new ItemLaundry(
                    jObj.getString("jenis"),
                    jObj.getInt("qty"),
                    jObj.getDouble("berat")
            );
            this.listItem.add(IL);
        }
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getTglAmbil() {
        return tglAmbil;
    }

    public void setTglAmbil(String tglAmbil) {
        this.tglAmbil = tglAmbil;
    }

    public JSONObject getTipeLayanan() {
        return tipeLayanan;
    }

    public void setTipeLayanan(JSONObject tipeLayanan) {
        this.tipeLayanan = tipeLayanan;
    }

    public List<ItemLaundry> getListItem() {
        return listItem;
    }
    
    public String getListItemJSON() {
        JSONArray jArr = new JSONArray();
        for(ItemLaundry IL : listItem) {
            JSONObject jObj = new JSONObject();
            jObj.put("jenis", IL.getJenis());
            jObj.put("qty", IL.getQty());
            jObj.put("berat", IL.getBerat());
            jArr.put(jObj);
        }
        return jArr.toString();
    }

    public void addItemLaundry(ItemLaundry item) {
        this.listItem.add(item);
    }

    public List<JobHistory> getListHistory() {
        return listHistory;
    }

    public void addJobHistory(JobHistory item) {
        this.listHistory.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double hitungDurasi(){
        return 1; // dummy
    }

    public double hitungBonus(){
        return 1; //dummy
    }

    public double hitungBerat(){
        return 1; //dummy
    }

    public double hitungTotalBiaya(){
        return 1; // dummy
    }
}
