package model;

import java.util.*;
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

    public Transaksi(int idTransaksi, String status, String tglMasuk, String tglSelesai, String tglAmbil, JSONObject tipeLayanan, Customer customer) {
        this.idTransaksi = idTransaksi;
        this.status = status;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.tipeLayanan = tipeLayanan;
        this.customer = customer;
    }
    
    public Transaksi(int idTransaksi, String status, String tglMasuk, String tglSelesai, String tglAmbil, String tipeLayanan, Customer customer) {
        this.idTransaksi = idTransaksi;
        this.status = status;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.tipeLayanan = new JSONObject(tipeLayanan);
        this.customer = customer;
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

    public void setListItem(List<ItemLaundry> listItem) {
        this.listItem = listItem;
    }

    public List<JobHistory> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<JobHistory> listHistory) {
        this.listHistory = listHistory;
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
