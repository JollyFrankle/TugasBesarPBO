package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Transaksi {
    // konstanta: DEFAULT_DTF itu y-M-d H:m:s
    public static final DateTimeFormatter DEFAULT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LOCAL_DTF = DateTimeFormatter.ofPattern("E, d MMM yyyy, HH.mm", new java.util.Locale("id"));
    private int id;
    private String lastActivity;
    private LocalDateTime tglLastActivity;
    private LocalDateTime tglMasuk;
    private LocalDateTime tglSelesai;
    private LocalDateTime tglAmbil;
    private JSONObject tipeLayanan;
    private float beratPakaian;
    private float beratSelimut;
    private float beratBoneka;
    private List<JobHistory> listHistory;
    private Customer customer;

    public Transaksi(int id, LocalDateTime tglMasuk, LocalDateTime tglSelesai, LocalDateTime tglAmbil, JSONObject tipeLayanan, float beratPakaian, float beratSelimut, float beratBoneka, Customer customer) {
        // Constructor untuk insert/update:
        this.id = id;
        this.lastActivity = null;
        this.tglLastActivity = null;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.tipeLayanan = tipeLayanan;
        this.beratPakaian = beratPakaian;
        this.beratSelimut = beratSelimut;
        this.beratBoneka = beratBoneka;
        this.customer = customer;
    }
    
    public Transaksi(int id, String lastActivity, String tglLastActivity, String tglMasuk, String tglSelesai, String tglAmbil, String tipeLayanan, float beratPakaian, float beratSelimut, float beratBoneka, Customer customer) {
        // Constructor saat mendapatkan query dari database:
        this.id = id;
        this.lastActivity = lastActivity;
        this.tglLastActivity = LocalDateTime.parse(tglLastActivity, Transaksi.DEFAULT_DTF); ;
        this.tglMasuk = LocalDateTime.parse(tglMasuk, Transaksi.DEFAULT_DTF); 
        this.tglSelesai = LocalDateTime.parse(tglSelesai, Transaksi.DEFAULT_DTF);
        this.tglAmbil = tglAmbil != null ? LocalDateTime.parse(tglAmbil, Transaksi.DEFAULT_DTF) : null;
        this.tipeLayanan = new JSONObject(tipeLayanan);
        this.beratPakaian = beratPakaian;
        this.beratSelimut = beratSelimut;
        this.beratBoneka = beratBoneka;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int idTransaksi) {
        this.id = idTransaksi;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }
    
    public String getLastActivity() {
        return lastActivity;
    }
    
    public LocalDateTime getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(LocalDateTime tglMasuk) {
        this.tglMasuk = tglMasuk;
    }
    
    public LocalDateTime getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(LocalDateTime tglSelesai) {
        this.tglSelesai = tglSelesai;
    }
    
    public LocalDateTime getTglAmbil() {
        return tglAmbil;
    }

    public void setTglAmbil(LocalDateTime tglAmbil) {
        this.tglAmbil = tglAmbil;
    }

    public JSONObject getTipeLayanan() {
        return tipeLayanan;
    }

    public void setTipeLayanan(JSONObject tipeLayanan) {
        this.tipeLayanan = tipeLayanan;
    }

    public List<JobHistory> getListHistory() {
        return listHistory;
    }

    public void addJobHistory(JobHistory item) {
        this.listHistory.add(item);
    }
    
    public void removeJobHistory(int index) {
        this.listHistory.remove(index);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getBeratPakaian() {
        return beratPakaian;
    }

    public void setBeratPakaian(float beratPakaian) {
        this.beratPakaian = beratPakaian;
    }

    public float getBeratSelimut() {
        return beratSelimut;
    }

    public void setBeratSelimut(float beratSelimut) {
        this.beratSelimut = beratSelimut;
    }

    public float getBeratBoneka() {
        return beratBoneka;
    }

    public void setBeratBoneka(float beratBoneka) {
        this.beratBoneka = beratBoneka;
    }
    
    public float getTotalBerat() {
        return this.beratBoneka + this.beratPakaian + this.beratSelimut;
    }

    public LocalDateTime getTglLastActivity() {
        return tglLastActivity;
    }

    public void setTglLastActivity(LocalDateTime tglLastActivity) {
        this.tglLastActivity = tglLastActivity;
    }
}
